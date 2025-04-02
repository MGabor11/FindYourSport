package com.marossolutions.findyoursport.plugins

import com.marossolutions.findyoursport.db.SportPlaceService
import com.marossolutions.findyoursport.db.tables.SportPlaces
import com.marossolutions.findyoursport.service.DispatcherProvider
import io.ktor.server.application.Application
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.get

fun Application.configureDatabases() {
    val db = Database.connect(
        url = "jdbc:mysql://localhost:3306/sports",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "PASSWORD"
    )

    transaction(db) {
        SchemaUtils.create(SportPlaces)
    }
}

suspend fun <T> CoroutineDispatcher.transaction(block: suspend () -> T): T =
    newSuspendedTransaction(this) { block() }
