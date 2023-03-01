package com.fabpps.deeplinkexecutor.di

import com.fabpps.deeplinkexecutor.di.modules.DaoModule
import com.fabpps.deeplinkexecutor.di.modules.RepositoryModules
import com.fabpps.deeplinkexecutor.di.modules.UseCaseModules
import com.fabpps.deeplinkexecutor.di.modules.ViewModelModules

class ModulesProviders {

    fun provide() = listOf(
        DaoModule().provide(),
        UseCaseModules().provide(),
        RepositoryModules().provides(),
        ViewModelModules().provide()
    ).flatten()
}