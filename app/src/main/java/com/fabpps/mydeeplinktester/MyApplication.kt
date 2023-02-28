package com.fabpps.mydeeplinktester

import android.app.Application
import com.fabpps.mydeeplinktester.di.ModulesProviders
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modulesProviders = ModulesProviders().provide()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(modulesProviders)
        }
    }
}