package com.marossolutions.findyoursport.db

import com.marossolutions.findyoursport.model.SportPlace

interface SportPlaceService {
    suspend fun addSportPlace(sportPlace: SportPlace): SportPlace?
    suspend fun getAllSportPlaces(): List<SportPlace>
    suspend fun deleteSportPlace(id: Int): Boolean
}