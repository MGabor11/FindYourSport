package com.marossolutions.findyoursport.db

import com.marossolutions.findyoursport.db.tables.SportPlaces
import com.marossolutions.findyoursport.model.SportPlace
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class SportPlaceServiceImpl() : SportPlaceService {

    private fun resultRowToSportPlace(row: ResultRow): SportPlace = SportPlace(
        id = row[SportPlaces.id],
        name = row[SportPlaces.name],
        location = row[SportPlaces.location],
        description = row[SportPlaces.description]
    )

    // TODO Dispatcher handling
    override suspend fun addSportPlace(sportPlace: SportPlace): SportPlace? =
        newSuspendedTransaction(Dispatchers.IO) {
            val insertStmt = SportPlaces.insert {
                it[name] = sportPlace.name
                it[location] = sportPlace.location
                it[description] = sportPlace.description
            }

            insertStmt.resultedValues?.singleOrNull()?.let { resultRowToSportPlace(it) }
        }

    override suspend fun getAllSportPlaces(): List<SportPlace> =
        newSuspendedTransaction(Dispatchers.IO) {
            SportPlaces.selectAll().map { resultRowToSportPlace(it) }
        }

    override suspend fun deleteSportPlace(id: Int): Boolean =
        newSuspendedTransaction(Dispatchers.IO) {
            SportPlaces.deleteWhere { SportPlaces.id eq id } > 0
        }
}