package com.marossolutions.findyoursport

import com.marossolutions.findyoursport.plugins.configureDI
import com.marossolutions.findyoursport.plugins.configureDatabases
import com.marossolutions.findyoursport.plugins.configureMonitoring
import com.marossolutions.findyoursport.plugins.configureRouting
import com.marossolutions.findyoursport.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureDI()
    configureSerialization()
    configureDatabases()
    configureRouting()
}