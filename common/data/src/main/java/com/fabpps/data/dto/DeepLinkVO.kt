package com.fabpps.data.dto

import android.os.Parcelable
import com.fabpps.data.dao.DeepLinkEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeepLinkVO(
    val deepLinkId: Long,
    val deepLink: String,
    val deepLinkAlias: String,
    val deepLinkDescription: String,
    val deepLinkIsFavorite: Boolean,
    val deepLinkPosition: Int
) : Parcelable {
    fun toDeepLinkEntity(): DeepLinkEntity {
        return DeepLinkEntity(
            deepLinkId = this.deepLinkId,
            deepLink = this.deepLink,
            deepLinkAlias = this.deepLinkAlias,
            deepLinkDescription = this.deepLinkDescription,
            deepLinkIsFavorite = this.deepLinkIsFavorite,
            deepLinkPosition = this.deepLinkPosition
        )
    }
}
