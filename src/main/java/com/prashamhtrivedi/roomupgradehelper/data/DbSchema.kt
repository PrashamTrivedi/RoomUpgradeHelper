package com.prashamhtrivedi.roomupgradehelper.data


import com.squareup.moshi.Json


data class DbSchema(@field:Json(name = "formatVersion") val formatVersion: Int? = 0, @field:Json(
        name = "database") val database: Database? = Database())

data class Database(@field:Json(name = "version") val version: Int = 0, @field:Json(name = "identityHash") val identityHash: String? = "", @field:Json(
        name = "entities") val entities: List<Entity?>? = listOf(), @field:Json(name = "setupQueries") val setupQueries: List<String?>? = listOf())

data class Entity(@field:Json(name = "tableName") val tableName: String? = "", @field:Json(name = "createSql") val createSql: String? = "", @field:Json(
        name = "fields") val fields: List<Field?>? = listOf(), @field:Json(name = "primaryKey") val primaryKey: PrimaryKey? = PrimaryKey(), @field:Json(
        name = "indices") val indices: List<Indice?>? = listOf(), @field:Json(name = "foreignKeys") val foreignKeys: List<ForeignKey?>? = listOf())

data class Indice(@field:Json(name = "name") val name: String? = "", @field:Json(name = "unique") val unique: Boolean? = false, @field:Json(
        name = "columnNames") val columnNames: List<String?>? = listOf(), @field:Json(name = "createSql") val createSql: String? = "")

data class PrimaryKey(@field:Json(name = "columnNames") val columnNames: List<String?>? = listOf(), @field:Json(
        name = "autoGenerate") val autoGenerate: Boolean? = false)

data class Field(@field:Json(name = "fieldPath") val fieldPath: String? = "", @field:Json(name = "columnName") val columnName: String? = "", @field:Json(
        name = "affinity") val affinity: String? = "", @field:Json(name = "notNull") val notNull: Boolean? = false)

data class ForeignKey(@Json(name = "table") val table: String? = "", @Json(name = "onDelete") val onDelete: String? = "", @Json(
        name = "onUpdate") val onUpdate: String? = "", @Json(name = "columns") val columns: List<String?>? = listOf(), @Json(
        name = "referencedColumns") val referencedColumns: List<String?>? = listOf())
