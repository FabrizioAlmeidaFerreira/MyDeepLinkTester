package com.fabpps.deeplinkexecutor.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface FeatureComponent : KoinComponent {

    override fun getKoin(): Koin {
        return FeatureKoinContext.koinApp.koin
    }
}