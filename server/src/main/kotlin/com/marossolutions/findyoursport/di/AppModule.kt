package com.marossolutions.findyoursport.di

import com.marossolutions.findyoursport.db.SportPlaceService
import com.marossolutions.findyoursport.db.SportPlaceServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<SportPlaceService> {
        SportPlaceServiceImpl()
    }
}