package com.marossolutions.findyoursport.service

import com.marossolutions.findyoursport.model.SportPlace
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.withContext

private const val API_URL = "sport-places"

class SportPlacesServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcherProvider: DispatcherProvider,
) : SportPlacesService {

    override suspend fun getSportPlaces(): List<SportPlace> = withContext(dispatcherProvider.io) {
        val response = httpClient.get(API_URL).body<List<SportPlace>>()
        return@withContext response
    }

    override suspend fun addSportPlace(sportPlace: SportPlace): SportPlace =
        withContext(dispatcherProvider.io) {
            val response = httpClient.post(API_URL) {
                setBody(sportPlace)
            }.body<SportPlace>()
            return@withContext response
        }

    override suspend fun removeSportPlace(sportPlaceId: Int) {
        withContext(dispatcherProvider.io) {
            httpClient.delete("$API_URL/$sportPlaceId")
        }
    }
}