package com.pandulapeter.buildinglist

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BuildingListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BuildingListApplication)
            modules(modules)
        }
    }
}