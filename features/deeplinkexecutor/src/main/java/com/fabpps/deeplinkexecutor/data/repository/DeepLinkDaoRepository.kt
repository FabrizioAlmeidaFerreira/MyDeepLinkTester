package com.fabpps.deeplinkexecutor.data.repository

import androidx.annotation.WorkerThread
import com.fabpps.dao.DeepLinkRoomDatabase
import com.fabpps.data.dao.DeepLinkEntity
import com.fabpps.extensions.DefaultDispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeepLinkDaoRepository(
    private val dao: DeepLinkRoomDatabase
) {

    val allDeepLinks: Flow<List<DeepLinkEntity>> = dao.deepLinkDao().getAllDeepLinks()

    @WorkerThread
    suspend fun insertDeepLinkOnDao(deepLinkEntity: DeepLinkEntity) {
        dao.deepLinkDao().insertDeepLink(deepLinkEntity)
    }

    suspend fun checkIfExists(deepLink: String): Flow<Result<Boolean>> {
        return flow {
            try {
                emit(
                    Result.success(dao.deepLinkDao().getVerifyExistsDeepLink(deepLink).isEmpty())
                )
            } catch (e: Exception) {
                emit(Result.success(false))
            }
        }
    }
    @WorkerThread
    suspend fun updateDeepLink(deepLinkEntity: DeepLinkEntity) {
        dao.deepLinkDao().updateDeepLink(deepLinkEntity)
    }

    @WorkerThread
    suspend fun deleteDeepLink(deepLinkEntity: DeepLinkEntity) {
        dao.deepLinkDao().deleteDeepLink(deepLinkEntity)
    }
}