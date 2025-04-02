package com.marossolutions.findyoursport.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedApplicationModule = module {
    includes(
        coreModule,
        serviceModule,
        repositoryModule,
        viewModelModule,
        navigationModule,
        networkModule
    )
}
