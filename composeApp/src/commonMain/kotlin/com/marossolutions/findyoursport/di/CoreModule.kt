package com.marossolutions.findyoursport.di

import com.marossolutions.findyoursport.SERVER_PORT
import com.marossolutions.findyoursport.di.qualifier.serverUrl
import com.marossolutions.findyoursport.localServerHost
import org.koin.dsl.module

val coreModule = module {
    single<String>(qualifier = serverUrl) { "http://$localServerHost:$SERVER_PORT/" }
}