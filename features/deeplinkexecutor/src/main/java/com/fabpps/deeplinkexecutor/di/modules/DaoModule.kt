package com.fabpps.deeplinkexecutor.di.modules

import com.fabpps.dao.DeepLinkRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class DaoModule {

    fun provide() = listOf(daoModule)

    private val daoModule = module {
        single { DeepLinkRoomDatabase.getDataBase(androidContext()) }
    }
}