package com.marossolutions.findyoursport.repository

import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.service.SportPlacesService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SportPlacesRepositoryImpl(
    private val sportPlacesService: SportPlacesService
) : SportPlacesRepository {
    private val _sportPlaces = MutableStateFlow<List<SportPlace>?>(null)

    override val sportPlaces: StateFlow<List<SportPlace>?> = _sportPlaces.asStateFlow()

    override suspend fun fetchSportPlaces() {
        val response = sportPlacesService.getSportPlaces()
        _sportPlaces.value = response
    }

    override suspend fun addSportPlace(sportPlace: SportPlace) {
        val response = sportPlacesService.addSportPlace(sportPlace)
        _sportPlaces.value = _sportPlaces.value.orEmpty().plus(response)
    }

    override suspend fun removeSportPlace(sportPlaceId: Int) {
        sportPlacesService.removeSportPlace(sportPlaceId)
        _sportPlaces.value = _sportPlaces.value.orEmpty().filter { it.id != sportPlaceId }
    }
}
