package com.marossolutions.findyoursport.di

import org.koin.dsl.bind
import org.koin.dsl.module
import com.marossolutions.findyoursport.repository.SportPlacesRepository
import com.marossolutions.findyoursport.repository.SportPlacesRepositoryImpl
import org.koin.core.module.dsl.singleOf

val repositoryModule = module {
    singleOf(::SportPlacesRepositoryImpl).bind<SportPlacesRepository>()
}