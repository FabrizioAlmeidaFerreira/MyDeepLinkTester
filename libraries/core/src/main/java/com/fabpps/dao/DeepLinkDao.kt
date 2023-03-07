package com.fabpps.dao

import androidx.room.*
import com.fabpps.data.dao.DeepLinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeepLinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeepLink(deepLinkEntity: DeepLinkEntity)

    @Query(
        "SELECT * FROM DeepLinkEntity " +
                "WHERE deepLink LIKE '%' || :searchQuery || '%' ORDER BY " +
                "CASE WHEN :favorites == true THEN deepLinkIsFavorite END DESC," +
                "CASE WHEN :favorites == false THEN deepLinkId END DESC"
    )
    fun getAllDeepLinks(searchQuery: String, favorites: Boolean = false) : Flow<List<DeepLinkEntity>>

    @Query("SELECT * FROM DeepLinkEntity WHERE deepLink IN (:deepLink)")
    suspend fun getVerifyExistsDeepLink(deepLink: String) : List<DeepLinkEntity>

    @Update
    suspend fun updateDeepLink(deepLinkEntity: DeepLinkEntity)

    @Delete
    suspend fun deleteDeepLink(vararg deepLinkEntity: DeepLinkEntity)
}