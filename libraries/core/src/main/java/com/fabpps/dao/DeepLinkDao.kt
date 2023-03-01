package com.fabpps.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.fabpps.data.dao.DeepLinkEntity

@Dao
interface DeepLinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeepLink(deepLinkEntity: DeepLinkEntity)

}