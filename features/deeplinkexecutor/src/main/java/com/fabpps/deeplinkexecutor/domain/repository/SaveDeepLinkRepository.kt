package com.fabpps.deeplinkexecutor.domain.repository

import com.fabpps.dao.DeepLinkDao
import com.fabpps.dao.DeepLinkRoomDatabase
import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.extensions.DefaultDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveDeepLinkRepository(
    private val dao: DeepLinkRoomDatabase,
    private val dispatcherProvider: DefaultDispatcherProvider
) {
    suspend fun insertDeepLink(deepLinkEntity: DeepLinkEntity): Flow<Result<Any>> {
        return flow {
            try {
                dao.deepLinkDao().insertDeepLink(deepLinkEntity = deepLinkEntity)
                emit(Result.success(true))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }.flowOn(dispatcherProvider.io())
    }
}