package com.marossolutions.findyoursport.repository

import com.marossolutions.findyoursport.model.SportPlace
import kotlinx.coroutines.flow.StateFlow

interface SportPlacesRepository {

    val sportPlaces: StateFlow<List<SportPlace>?>

    suspend fun fetchSportPlaces()

    suspend fun addSportPlace(sportPlace: SportPlace)

    suspend fun removeSportPlace(sportPlaceId: Int)
}
