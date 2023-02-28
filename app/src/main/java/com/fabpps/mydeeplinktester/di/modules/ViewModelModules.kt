package com.fabpps.mydeeplinktester.di.modules

import com.fabpps.mydeeplinktester.ui.MainViewModel
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