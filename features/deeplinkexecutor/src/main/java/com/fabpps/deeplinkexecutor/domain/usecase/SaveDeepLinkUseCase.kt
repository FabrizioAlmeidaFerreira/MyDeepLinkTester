package com.fabpps.deeplinkexecutor.domain.usecase

import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.deeplinkexecutor.domain.repository.SaveDeepLinkRepository

class SaveDeepLinkUseCase(
    private val saveDeepLinkRepository: SaveDeepLinkRepository
) {
    suspend fun saveDeepLink(deepLinkEntity: DeepLinkEntity) =
        saveDeepLinkRepository.insertDeepLink(deepLinkEntity)
}