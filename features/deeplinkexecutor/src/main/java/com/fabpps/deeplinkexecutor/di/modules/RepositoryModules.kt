package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.data.repository.DeepLinkDaoRepository
import org.koin.dsl.module

class RepositoryModules {

    fun provides() = listOf(deepLinkDaoRepository)

    private val deepLinkDaoRepository = module {
        single { DeepLinkDaoRepository(get()) }
    }
}