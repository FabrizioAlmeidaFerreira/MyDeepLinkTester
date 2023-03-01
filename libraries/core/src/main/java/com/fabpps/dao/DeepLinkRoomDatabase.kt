package com.fabpps.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fabpps.data.dao.DeepLinkEntity

@Database(entities = [DeepLinkEntity::class], version = 1, exportSchema = false)
public abstract class DeepLinkRoomDatabase : RoomDatabase() {

    abstract fun deepLinkDao() : DeepLinkDao

    companion object {
        @Volatile
        private var INSTANCE : DeepLinkRoomDatabase? = null

        fun getDataBase(context: Context) : DeepLinkRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeepLinkRoomDatabase::class.java,
                    "deep_link_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}