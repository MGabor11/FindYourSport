package com.marossolutions.findyoursport

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform