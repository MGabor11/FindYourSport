package com.marossolutions.findyoursport.routes

import com.marossolutions.findyoursport.db.SportPlaceService
import com.marossolutions.findyoursport.model.SportPlace
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.sportPlaceRoute(sportPlaceService: SportPlaceService) {
    route("/sport-places") {
        get {
            val sportPlaces = sportPlaceService.getAllSportPlaces()
            call.respond(HttpStatusCode.OK, sportPlaces)
        }
        post {
            val sportPlace = call.receive<SportPlace>()
            sportPlaceService.addSportPlace(sportPlace)?.let {
                call.respond(HttpStatusCode.Created, it)
            } ?: call.respond(HttpStatusCode.BadRequest, "Error!!")
        }
        delete("/{id}") {
            call.parameters["id"]?.toInt()?.let {
                if (sportPlaceService.deleteSportPlace(it)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            } ?: call.respond(HttpStatusCode.BadRequest, "Provide Id!!")
        }
    }
}
