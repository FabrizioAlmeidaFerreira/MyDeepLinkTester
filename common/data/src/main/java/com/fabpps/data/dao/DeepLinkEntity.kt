package com.fabpps.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fabpps.data.dto.DeepLinkVO

@Entity
data class DeepLinkEntity(
    @PrimaryKey(autoGenerate = true) val deepLinkId: Long = 0,
    val deepLink: String,
    val deepLinkAlias: String,
    val deepLinkDescription: String,
    val deepLinkIsFavorite: Boolean,
    val deepLinkPosition: Int
) {
    fun toDeepLinkVO() :DeepLinkVO {
        return DeepLinkVO(
            deepLinkId = this.deepLinkId,
            deepLink = this.deepLink,
            deepLinkAlias = this.deepLinkAlias,
            deepLinkDescription = this.deepLinkDescription,
            deepLinkIsFavorite = this.deepLinkIsFavorite,
            deepLinkPosition = this.deepLinkPosition
        )
    }
}
