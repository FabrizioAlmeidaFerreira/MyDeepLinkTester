package com.fabpps.deeplinkexecutor.di

import com.fabpps.deeplinkexecutor.init.FeatureParams
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

object FeatureKoinContext {

    lateinit var koinApp: KoinApplication

    fun initialize(params: FeatureParams) {
        val modulesProviders = ModulesProviders().provide()

        koinApp = koinApplication {
            androidContext(params.application)
            modules(modulesProviders)
        }
    }
}