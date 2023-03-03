package com.fabpps.deeplinkexecutor.ui.adapter.favorite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.domain.interfaces.DeepLinkAdapterListeners
import com.fabpps.deeplinkexecutor.utils.extensions.setOnClickListenerWithDelay
import com.google.android.material.textview.MaterialTextView

class DeepLinkFavoriteViewHolder(
    itemView: View,
    private val deepLinkAdapterListeners: DeepLinkAdapterListeners
) : RecyclerView.ViewHolder(itemView) {

    private val tvDeepLink: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLink) }
    private val tvDeepLinkDescription: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLinkDescription) }
    private val ivSetFavorite: ImageView by lazy { itemView.findViewById(R.id.ivSetFavorite) }
    private val ivDelete: ImageView by lazy { itemView.findViewById(R.id.ivDelete) }

    fun binding(deepLinkVO: DeepLinkVO) {
        tvDeepLink.text = deepLinkVO.deepLink
        tvDeepLinkDescription.text = deepLinkVO.deepLinkDescription

        itemView.setOnClickListenerWithDelay {
            deepLinkAdapterListeners.onDeepLinkItemSelected(deepLinkVO)
        }

        ivDelete.setOnClickListenerWithDelay {
            deepLinkAdapterListeners.onDeleteDeepLink(deepLinkVO)
        }

        ivSetFavorite.apply {
            setCurrentFavoriteImage(deepLinkVO.deepLinkIsFavorite)

            setOnClickListenerWithDelay {
                deepLinkAdapterListeners.onDeepLinkUpdate(
                    deepLinkVO = deepLinkVO.copy(deepLinkIsFavorite = !deepLinkVO.deepLinkIsFavorite)
                )
            }
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