package com.fabpps.deeplinkexecutor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.domain.usecase.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class DeepLinkExecutorViewModel(
    private val saveDeepLinkUseCase: SaveDeepLinkUseCase,
    private val getAllDeepLinkUseCase: GetAllDeepLinkUseCase,
    private val checkIfExistsDeepLinkUseCase: CheckIfExistsDeepLinkUseCase,
    private val updateDeepLinkUseCase: UpdateDeepLinkUseCase,
    private val deleteDeepLinkUseCase: DeleteDeepLinkUseCase
) : ViewModel() {

    private val job = Job()

    val searchQuery:  MutableStateFlow<Pair<String, Boolean>> = MutableStateFlow(Pair("", false))

    @OptIn(ExperimentalCoroutinesApi::class)
    private val taskFlow = searchQuery.flatMapLatest {
        getAllDeepLinkUseCase.getAllDeepLink(it.first, it.second)
    }

    val allDeepLinks: LiveData<List<DeepLinkEntity>> = taskFlow.asLiveData()

    fun saveDeepLink(deepLinkText: String) = checkToSaveOnRoom(deepLinkText)

    private fun checkToSaveOnRoom(deepLinkText: String) {
        viewModelScope.launch(job + Dispatchers.IO) {
            checkIfExistsDeepLinkUseCase.invoke(deepLinkText).collect {
                when {
                    it.isSuccess -> {
                        if (it.getOrNull() == true) {
                            saveOnRoomDeepLink(deepLinkText)
                        }
                    }
                }
            }
        }
    }

    private fun saveOnRoomDeepLink(deepLinkText: String) {
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

    fun updateDeepLink(deepLinkVO: DeepLinkVO) {
        viewModelScope.launch(job + Dispatchers.IO) {
            updateDeepLinkUseCase.invoke(deepLinkVO.toDeepLinkEntity())
        }
    }

    fun deleteDeepLink(deepLinkVO: DeepLinkVO) {
        viewModelScope.launch(job + Dispatchers.IO) {
            deleteDeepLinkUseCase.invoke(deepLinkVO.toDeepLinkEntity())
        }
    }

    fun cancelAllJobs() {
        job.cancel()
    }
}