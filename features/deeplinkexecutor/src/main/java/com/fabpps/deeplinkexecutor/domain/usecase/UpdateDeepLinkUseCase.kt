package com.fabpps.deeplinkexecutor.domain.usecase

import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.deeplinkexecutor.data.repository.DeepLinkDaoRepository

class UpdateDeepLinkUseCase(
    private val deepLinkDaoRepository: DeepLinkDaoRepository
) {
    suspend operator fun invoke(deepLinkEntity: DeepLinkEntity) =
        deepLinkDaoRepository.updateDeepLink(deepLinkEntity)
}