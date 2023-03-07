package com.fabpps.deeplinkexecutor.data.repository

import androidx.annotation.WorkerThread
import com.fabpps.dao.DeepLinkRoomDatabase
import com.fabpps.data.dao.DeepLinkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeepLinkDaoRepository(
    private val dao: DeepLinkRoomDatabase
) {
    @WorkerThread
    fun geAllDeepLinks(searchQuery: String, favorites: Boolean = false): Flow<List<DeepLinkEntity>> {
        return dao.deepLinkDao().getAllDeepLinks(searchQuery, favorites)
    }

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