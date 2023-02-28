package com.fabpps.mydeeplinktester.di

import com.fabpps.mydeeplinktester.di.modules.ViewModelModules

class ModulesProviders {

    fun provide() = listOf(
        ViewModelModules().provide()
    ).flatten()
}