package com.fabpps.deeplinkexecutor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.deeplinkexecutor.domain.usecase.SaveDeepLinkUseCase
import kotlinx.coroutines.launch

class DeepLinkExecutorViewModel(
    private val saveDeepLinkUseCase: SaveDeepLinkUseCase
) : ViewModel() {

    fun saveDeepLink() {
        viewModelScope.launch {
            saveDeepLinkUseCase.saveDeepLink(
                DeepLinkEntity(
                    deepLink = "iti://gestaofinanceira",
                    deepLinkAlias = "hello word",
                    deepLinkPosition = 0,
                    deepLinkDescription = "first description"
                )
            )
        }
    }
}