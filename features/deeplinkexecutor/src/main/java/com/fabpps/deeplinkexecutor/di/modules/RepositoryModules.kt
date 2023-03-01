package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.domain.repository.SaveDeepLinkRepository
import com.fabpps.extensions.DefaultDispatcherProvider
import org.koin.dsl.module

class RepositoryModules {

    fun provides() = listOf(saveDeepLinkRepository)

    private val saveDeepLinkRepository = module {
        factory { SaveDeepLinkRepository(get(), DefaultDispatcherProvider()) }
    }
}