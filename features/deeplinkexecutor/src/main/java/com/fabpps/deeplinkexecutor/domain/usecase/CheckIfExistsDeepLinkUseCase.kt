package com.fabpps.deeplinkexecutor.domain.usecase

import com.fabpps.deeplinkexecutor.data.repository.DeepLinkDaoRepository

class CheckIfExistsDeepLinkUseCase(
    private val deepLinkDaoRepository: DeepLinkDaoRepository
) {
    suspend operator fun invoke(deepLink: String) = deepLinkDaoRepository.checkIfExists(deepLink)
}