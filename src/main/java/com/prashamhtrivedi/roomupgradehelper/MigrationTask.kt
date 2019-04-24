package com.prashamhtrivedi.roomupgradehelper

import com.prashamhtrivedi.roomupgradehelper.data.Database
import com.prashamhtrivedi.roomupgradehelper.data.DbSchema
import com.squareup.moshi.Moshi
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.lang.IllegalArgumentException

//To make this class work, schema must be exported.
// To export the schema, follow Prerequisites section from below link
//https://medium.com/google-developers/testing-room-migrations-be93cdb0d975#6872


open class MigrationTask : DefaultTask() {


    @OutputFile
    var output = project.file("${project.buildDir}/roomupgradehelper/output.txt")

    @InputFile
    var input = project.file("${project.buildDir}/roomupgradehelper/inputDir.txt").apply {
        parentFile.mkdirs()
        createNewFile()
        writeText("")
    }

    val moshi = Moshi.Builder().build()

    @TaskAction
    fun action() {


        val extension = project.extensions.run {
            findByName("roomUpgrade") as RoomHelperConfiguration
        }

        if (!extension.jsonPath.isBlank()) {
            output.parentFile.mkdirs()
            output.createNewFile()
            val dbSchemas = getSchemas(extension.jsonPath)

            dbSchemas.forEach {
                input.appendText("${it.database?.version}")
            }

            dbSchemas.reduce { acc, dbSchema ->
                println("From version ${acc.database?.version} to ${dbSchema.database?.version}")
                output.writeText("From version ${acc.database?.version} to ${dbSchema.database?.version}")
                output.writeText("========")
                println("=========")
                if (acc.database != null && dbSchema.database != null) {
                    val migrationStatement = getMigrationStatement(acc.database, dbSchema.database)
                    for (statement in migrationStatement) {
                        output.writeText(statement)
                        println(statement)
                        output.writeText("\n")
                    }
                }
                output.writeText("\n")
                println()
                dbSchema
            }

        } else {
            throw IllegalArgumentException("roomUpgrade.path should be set")
        }
    }


    private fun readJson(file: File): String = file.readText()

    private fun convertDbSchemaFromJson(json: String): DbSchema? =
            moshi.adapter<DbSchema>(DbSchema::class.java).fromJson(json)

    private fun getSchemas(path: String): MutableList<DbSchema> {
        val tableInfoArray = mutableListOf<DbSchema>()
        val pathDir = File(path)
        if (!pathDir.isDirectory) throw IllegalArgumentException("The path should be a directory, it's a file")
        pathDir.listFiles().map {
            getTableInfo(it)
        }.forEach { dbSchema ->
            dbSchema?.let {
                tableInfoArray += it
            }
        }
        return tableInfoArray

    }

    private fun getTableInfo(file: File): DbSchema? {
        val jsonString = readJson(file)
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