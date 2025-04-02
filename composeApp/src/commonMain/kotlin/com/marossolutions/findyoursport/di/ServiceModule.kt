package com.marossolutions.findyoursport.di

import com.marossolutions.findyoursport.service.SportPlacesService
import com.marossolutions.findyoursport.service.SportPlacesServiceImpl
import com.marossolutions.findyoursport.service.DispatcherProvider
import com.marossolutions.findyoursport.service.DispatcherProviderImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::DispatcherProviderImpl).bind<DispatcherProvider>()
    singleOf(::SportPlacesServiceImpl).bind<SportPlacesService>()
}