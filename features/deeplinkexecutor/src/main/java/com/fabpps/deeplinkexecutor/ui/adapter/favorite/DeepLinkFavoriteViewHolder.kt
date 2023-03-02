package com.fabpps.deeplinkexecutor.ui.adapter.favorite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.utils.extensions.setOnClickListenerWithDelay
import com.google.android.material.textview.MaterialTextView

class DeepLinkFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvDeepLink: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLink) }
    private val tvDeepLinkDescription: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLinkDescription) }
    private val ivSetFavorite: ImageView by lazy { itemView.findViewById(R.id.ivSetFavorite) }

    fun binding(deepLinkVO: DeepLinkVO) {
        tvDeepLink.text = deepLinkVO.deepLink
        tvDeepLinkDescription.text = deepLinkVO.deepLinkDescription

        ivSetFavorite.apply {
            setCurrentFavoriteImage(deepLinkVO.deepLinkIsFavorite)

            setOnClickListenerWithDelay { setCurrentFavoriteImage(true) }
        }
    }

    private fun ImageView.setCurrentFavoriteImage(isFavorite: Boolean) {
        setImageResource(
            if (isFavorite) {
                com.fabpps.theme.R.drawable.ic_favorite
            } else {
                com.fabpps.theme.R.drawable.ic_favorite_false
            }
        )
    }
}