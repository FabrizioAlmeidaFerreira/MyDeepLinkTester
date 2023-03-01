package com.fabpps.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fabpps.data.dao.DeepLinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeepLinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeepLink(deepLinkEntity: DeepLinkEntity)

    @Query("SELECT * FROM DeepLinkEntity")
    fun getAllDeepLinks() : Flow<List<DeepLinkEntity>>

}