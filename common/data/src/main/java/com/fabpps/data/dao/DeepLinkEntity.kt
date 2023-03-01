package com.fabpps.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeepLinkEntity(
    @PrimaryKey(autoGenerate = true) val deepLinkId: Long = 0,
    val deepLink: String,
    val deepLinkAlias: String,
    val deepLinkDescription: String,
    val deepLinkPosition: Int
)
