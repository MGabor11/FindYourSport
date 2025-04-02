package com.marossolutions.findyoursport.service

import com.marossolutions.findyoursport.model.SportPlace

interface SportPlacesService {

    suspend fun getSportPlaces(): List<SportPlace>

    suspend fun addSportPlace(sportPlace: SportPlace): SportPlace

    suspend fun removeSportPlace(sportPlaceId: Int)
}
