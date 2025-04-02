package com.marossolutions.findyoursport.db

import com.marossolutions.findyoursport.db.tables.SportPlaces
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.plugins.transaction
import com.marossolutions.findyoursport.service.DispatcherProvider
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class SportPlaceServiceImpl(private val dispatcherProvider: DispatcherProvider) :
    SportPlaceService {

    private fun resultRowToSportPlace(row: ResultRow): SportPlace = SportPlace(
        id = row[SportPlaces.id],
        name = row[SportPlaces.name],
        location = row[SportPlaces.location],
        description = row[SportPlaces.description]
    )

    override suspend fun addSportPlace(sportPlace: SportPlace): SportPlace? =
        dispatcherProvider.io.transaction {
            val insertStmt = SportPlaces.insert {
                it[name] = sportPlace.name
                it[location] = sportPlace.location
                it[description] = sportPlace.description
            }

            insertStmt.resultedValues?.singleOrNull()?.let { resultRowToSportPlace(it) }
        }

    override suspend fun getAllSportPlaces(): List<SportPlace> =
        dispatcherProvider.io.transaction {
            SportPlaces.selectAll().map { resultRowToSportPlace(it) }
        }

    override suspend fun deleteSportPlace(id: Int): Boolean =
        dispatcherProvider.io.transaction {
            SportPlaces.deleteWhere { SportPlaces.id eq id } > 0
        }
}