package com.fabpps.deeplinkexecutor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.deeplinkexecutor.domain.usecase.GetAllDeepLinkUseCase
import com.fabpps.deeplinkexecutor.domain.usecase.SaveDeepLinkUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DeepLinkExecutorViewModel(
    private val saveDeepLinkUseCase: SaveDeepLinkUseCase,
    private val getAllDeepLinkUseCase: GetAllDeepLinkUseCase
) : ViewModel() {

    val allDeepLinks: LiveData<List<DeepLinkEntity>> =
        getAllDeepLinkUseCase.getAllDeepLink().asLiveData()

    fun saveDeepLink(deepLinkText: String) {
        viewModelScope.launch {
            saveDeepLinkUseCase.saveDeepLink(
                DeepLinkEntity(
                    deepLink = deepLinkText,
                    deepLinkAlias = "",
                    deepLinkPosition = 0,
                    deepLinkDescription = "",
                    deepLinkIsFavorite = false
                )
            )
        }
    }

}