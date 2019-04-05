package com.prashamhtrivedi.roomupgradehelper

import com.prashamhtrivedi.roomupgradehelper.data.Database
import com.prashamhtrivedi.roomupgradehelper.data.DbSchema
import com.squareup.moshi.Moshi
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

//To make this class work, schema must be exported.
// To export the schema, follow Prerequisites section from below link
//https://medium.com/google-developers/testing-room-migrations-be93cdb0d975#6872


open class MigrationTask : DefaultTask() {


    @OutputFile
    var output = project.file("${project.buildDir}/roomupgradehelper/output.txt")

    @InputFile
    var input = project.file("${project.buildDir}/roomupgradehelper/inputDir.txt")

    val moshi = Moshi.Builder().build()

    @TaskAction
    fun action() {
        val extension = project.extensions.run {
            findByName("roomUpgrade") as Configuration
        }

        if (!extension.path.isBlank()) {
            output.createNewFile()
            output.writeText("========")
            val dbSchemas = getSchemas()
            dbSchemas.reduce { acc, dbSchema ->
                if (acc.database != null && dbSchema.database != null) {
                    val migrationStatement = getMigrationStatement(acc.database, dbSchema.database)
                    for(statement in migrationStatement){
                        output.writeText(statement)
                        output.writeText("\n")
                    }
                }
                dbSchema
            }

        }
    }


    private fun readFileJson(fileName: String): String = File(fileName).readText()

    private fun convertDbSchemaFromJson(json:String): DbSchema? =
            moshi.adapter<DbSchema>(DbSchema::class.java).fromJson(json)

    private fun getSchemas(): MutableList<DbSchema> {


        val tableInfoArray = mutableListOf<DbSchema>()
        path.map { getTableInfoFromJson("$name/$it") }.forEach { dbSchema ->
            dbSchema?.let {
                tableInfoArray += it
            }
        }
        return tableInfoArray

    }

    private fun getTableJson(fileName: String): String = readFileJson(fileName = fileName)

    private fun getTableInfoFromJson(fileName: String): DbSchema? {
        val jsonString = getTableJson(fileName)
        return convertDbSchemaFromJson(jsonString)
    }





    private fun getMigrationStatement(first: Database, second: Database): MutableList<String> {

        val statementsToExecute = mutableListOf<String>()
        val firstEntities = first.entities ?: listOf()
        val secondEntities = second.entities ?: listOf()

        for (entity in secondEntities) {

            entity?.let { (tableName, _, fields, _, indices) ->

                val firstEntity = firstEntities.first { tableName == it?.tableName }


                //Compare Columns
                val firstColumns = firstEntity?.fields ?: listOf()
                val secondColumns = fields ?: listOf()

                val newColumns = secondColumns.filterNot { firstColumns.contains(it) }

                //Add new Columns
                for (column in newColumns) {
                    column?.let {
                        val constraint = if (it.notNull == true) "NOT NULL DEFAULT ${getDefaultValue(
                                it.affinity)}" else ""
                        val migrationStatement = "Alter table '$tableName' Add column '${it.columnName}' ${it.affinity} $constraint"

                        statementsToExecute += migrationStatement
                    }
                }

                //Indices
                val firstIndices = firstEntity?.indices ?: listOf()
                val secondIndices = indices ?: listOf()

                val newIndices = secondIndices.filterNot { firstIndices.contains(it) }

                for (newIndex in newIndices) {
                    newIndex?.let {
                        val migrationStatementDrop = "Drop Index ${it.name}"
                        val migrationStatementReCreate = it.createSql ?: ""



                        statementsToExecute += migrationStatementDrop
                        statementsToExecute += migrationStatementReCreate
                    }
                }
            }
        }

        return statementsToExecute


    }

    private fun getDefaultValue(affinity: String?): String = when (affinity ?: "") {
        "NUMBER" -> "0"
        "TEXT" -> "''"
        else -> "''"
    }


}