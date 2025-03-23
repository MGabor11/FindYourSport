package com.marossolutions.findyoursport.db.tables

import org.jetbrains.exposed.sql.Table

object SportPlaces : Table(name = "sport_places") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val location = varchar("location", 50)
    val description = varchar("description", 50).nullable()

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}