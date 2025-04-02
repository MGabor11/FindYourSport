package com.marossolutions.findyoursport.di

import com.marossolutions.findyoursport.viewmodel.AddSportPlaceViewModel
import com.marossolutions.findyoursport.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddSportPlaceViewModel)
}