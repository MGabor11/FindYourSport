package com.marossolutions.findyoursport.plugins

import com.marossolutions.findyoursport.db.tables.SportPlaces
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabases() {
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

suspend fun <T> transaction(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }
