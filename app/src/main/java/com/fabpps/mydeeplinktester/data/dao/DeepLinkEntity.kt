package com.fabpps.mydeeplinktester.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeepLinkEntity(
    @PrimaryKey(autoGenerate = true) val deepLinkId: Long,
    val deepLink: String,
    val deepLinkAlias: String,
    val deepLinkDescription: String,
    val deepLinkPosition: Int
)
