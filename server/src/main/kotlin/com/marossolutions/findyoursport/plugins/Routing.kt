package com.marossolutions.findyoursport.plugins

import com.marossolutions.findyoursport.db.SportPlaceService
import com.marossolutions.findyoursport.routes.sportPlaceRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.get

fun Application.configureRouting(sportPlaceService: SportPlaceService = get()) {
    routing {
        sportPlaceRoute(sportPlaceService)
    }
}