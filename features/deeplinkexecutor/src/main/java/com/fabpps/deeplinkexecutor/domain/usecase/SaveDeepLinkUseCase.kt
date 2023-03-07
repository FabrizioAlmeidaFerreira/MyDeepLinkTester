package com.fabpps.deeplinkexecutor.domain.usecase

import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.deeplinkexecutor.data.repository.DeepLinkDaoRepository

class SaveDeepLinkUseCase(
    private val deepLinkDaoRepository: DeepLinkDaoRepository
) {
    suspend fun saveDeepLink(deepLinkEntity: DeepLinkEntity) =
        deepLinkDaoRepository.insertDeepLinkOnDao(deepLinkEntity)
}