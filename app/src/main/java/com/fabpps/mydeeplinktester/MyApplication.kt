package com.fabpps.mydeeplinktester

import android.app.Application
import com.fabpps.mydeeplinktester.features.initFeaturesModules

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initFeaturesModules(application = this@MyApplication)
    }
}