package com.fabpps.deeplinkexecutor.di

import com.fabpps.deeplinkexecutor.di.modules.ViewModelModules

class ModulesProviders {

    fun provide() = listOf(
        ViewModelModules().provide()
    ).flatten()
}