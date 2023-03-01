package com.fabpps.deeplinkexecutor.domain.usecase

import com.fabpps.deeplinkexecutor.data.repository.DeepLinkDaoRepository

class GetAllDeepLinkUseCase(
    private val deepLinkDaoRepository: DeepLinkDaoRepository
) {
    fun getAllDeepLink() = deepLinkDaoRepository.allDeepLinks
}