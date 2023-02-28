package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModules {

    fun provide() = listOf(
        mainViewModel
    )

    private val mainViewModel = module {
        viewModel { MainViewModel() }
    }
}