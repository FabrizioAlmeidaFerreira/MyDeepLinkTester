package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.domain.usecase.SaveDeepLinkUseCase
import org.koin.dsl.module

class UseCaseModules {

    fun provide() = listOf(saveDeepLinkUseCase)

    private val saveDeepLinkUseCase = module {
        factory { SaveDeepLinkUseCase(get()) }
    }
}