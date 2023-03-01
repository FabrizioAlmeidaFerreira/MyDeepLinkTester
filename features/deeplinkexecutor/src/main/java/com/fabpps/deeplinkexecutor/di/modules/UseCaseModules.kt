package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.domain.usecase.GetAllDeepLinkUseCase
import com.fabpps.deeplinkexecutor.domain.usecase.SaveDeepLinkUseCase
import org.koin.dsl.module

class UseCaseModules {

    fun provide() = listOf(
        saveDeepLinkUseCase,
        getAllDeepLinkUseCase
    )

    private val saveDeepLinkUseCase = module {
        factory { SaveDeepLinkUseCase(get()) }
    }

    private val getAllDeepLinkUseCase = module {
        factory { GetAllDeepLinkUseCase(get()) }
    }
}