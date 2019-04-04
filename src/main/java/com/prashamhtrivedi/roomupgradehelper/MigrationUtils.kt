package com.prashamhtrivedi.roomupgradehelper

import com.prashamhtrivedi.roomupgradehelper.data.DbSchema
import com.squareup.moshi.Moshi

//To make this class work, schema must be exported.
// To export the schema, follow Prerequisites section from below link
//https://medium.com/google-developers/testing-room-migrations-be93cdb0d975#6872

//TODO: This is android version, we should make pure java version out of it. Most of work is already done here

class MigrationUtils(val moshi: Moshi) {


	private fun getSchemas(): MutableList<DbSchema> {

		Timber.tag("Schemas")
		val name = AppDatabase::class.java.name
		Timber.d(name)
		val path = this@MigrationUtils.context.assets.list(name)

		Timber.tag("Schemas")
		Timber.d(path.joinToString())
		val tableInfoArray = mutableListOf<DbSchema>()
		path.map { getTableInfoFromJson("$name/$it") }.forEach {
			it?.let {
				tableInfoArray += it
			}
		}
		return tableInfoArray

	}

	private fun getTableJson(fileName: String): String = readFileJson(fileName = fileName)

	private fun getTableInfoFromJson(fileName: String): DbSchema? {
		val jsonString = getTableJson(fileName)
		return jsonString.fromJson<DbSchema>(moshi)
	}


	fun getMigrations(): List<Migration> {

		val migrations = mutableListOf<Migration>()
		val dbSchemas = getSchemas()
		dbSchemas.reduce { acc, dbSchema ->
			if (acc.database != null && dbSchema.database != null) {
				migrations += getMigrationStatement(acc.database, dbSchema.database)
			}
			dbSchema
		}

		return migrations


	}

	private fun getMigrationStatement(first: Database, second: Database): Migration {

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
						Timber.d(migrationStatement)
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

						Timber.d(migrationStatementDrop)
						Timber.d(migrationStatementReCreate)

						statementsToExecute += migrationStatementDrop
						statementsToExecute += migrationStatementReCreate
					}
				}
			}
		}

		return object : Migration(first.version, second.version) {
			override fun migrate(database: SupportSQLiteDatabase) {
				try {
					for (statement in statementsToExecute) {
						Timber.tag("Statement")
						Timber.w(statement)
						database.execSQL(statement)
					}
				} catch (e: Exception) {
					e.printStackTrace()
				}
			}

		}

	}

	private fun getDefaultValue(affinity: String?): String = when (affinity ?: "") {
		"NUMBER" -> "0"
		"TEXT" -> "''"
		else -> "''"
	}


}