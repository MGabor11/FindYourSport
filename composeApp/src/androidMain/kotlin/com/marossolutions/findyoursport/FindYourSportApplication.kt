package com.marossolutions.findyoursport

import android.app.Application
import com.marossolutions.findyoursport.di.initKoin
import org.koin.android.ext.koin.androidContext

class FindYourSportApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FindYourSportApplication)
        }
    }
}
