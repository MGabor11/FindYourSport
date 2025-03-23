package com.marossolutions.findyoursport.model

import kotlinx.serialization.Serializable

@Serializable
data class SportPlace(
    val id: Int = 0,
    val name: String,
    val location: String,
    val description: String? = null,
)