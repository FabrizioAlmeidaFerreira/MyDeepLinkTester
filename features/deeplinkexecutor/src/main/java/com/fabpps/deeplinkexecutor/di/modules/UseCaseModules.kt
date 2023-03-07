package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.domain.usecase.*
import org.koin.dsl.module

class UseCaseModules {

    fun provide() = listOf(
        saveDeepLinkUseCase,
        getAllDeepLinkUseCase,
        checkIfExistsDeepLinkUseCase,
        updateDeepLinkUseCase,
        deleteDeepLinkUseCase
    )

    private val saveDeepLinkUseCase = module {
        factory { SaveDeepLinkUseCase(get()) }
    }

    private val getAllDeepLinkUseCase = module {
        factory { GetAllDeepLinkUseCase(get()) }
    }

    private val checkIfExistsDeepLinkUseCase = module {
        factory { CheckIfExistsDeepLinkUseCase(get()) }
    }

    private val updateDeepLinkUseCase = module {
        factory { UpdateDeepLinkUseCase(get()) }
    }

    private val deleteDeepLinkUseCase = module {
        factory { DeleteDeepLinkUseCase(get()) }
    }
}