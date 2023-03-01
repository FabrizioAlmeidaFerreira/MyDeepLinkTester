package com.fabpps.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeepLinkVO(
    val deepLinkId: Long,
    val deepLink: String,
    val deepLinkAlias: String,
    val deepLinkDescription: String,
    val deepLinkIsFavorite: Boolean,
    val deepLinkPosition: Int
) : Parcelable
