package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.deeplinkexecutor.ui.DeepLinkExecutorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModules {
    fun provide() = listOf(
        deepLinkExecutorViewModel
    )

    private val deepLinkExecutorViewModel = module {
        viewModel { DeepLinkExecutorViewModel(get(), get(), get(), get(), get()) }
    }
}