package com.marossolutions.findyoursport.di

import com.marossolutions.findyoursport.navigation.SimpleNavigator
import com.marossolutions.findyoursport.navigation.SimpleNavigatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val navigationModule = module {
    singleOf(::SimpleNavigatorImpl).bind<SimpleNavigator>()
}