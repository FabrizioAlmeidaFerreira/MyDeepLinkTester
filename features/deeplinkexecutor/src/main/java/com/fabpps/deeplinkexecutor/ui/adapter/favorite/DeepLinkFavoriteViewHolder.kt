package com.fabpps.deeplinkexecutor.ui.adapter.favorite

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.domain.interfaces.DeepLinkAdapterListeners
import com.fabpps.extensions.setOnClickListenerWithDelay
import com.fabpps.extensions.shareTextOnIntent
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class DeepLinkFavoriteViewHolder(
    itemView: View,
    private val deepLinkAdapterListeners: DeepLinkAdapterListeners
) : RecyclerView.ViewHolder(itemView) {

    private val tvDeepLink: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLink) }
    private val tvDeepLinkDescription: MaterialTextView by lazy { itemView.findViewById(R.id.tvDeepLinkDescription) }
    private val ivSetFavorite: MaterialButton by lazy { itemView.findViewById(R.id.ivSetFavorite) }
    private val ivDelete: MaterialButton by lazy { itemView.findViewById(R.id.ivDelete) }
    private val ivShare: MaterialButton by lazy { itemView.findViewById(R.id.ivShare) }

    fun binding(deepLinkVO: DeepLinkVO) {
        val deepLink = Uri.parse(deepLinkVO.deepLink)
        tvDeepLink.text = deepLink.scheme
        tvDeepLinkDescription.text = deepLink.toString()

        itemView.setOnClickListenerWithDelay {
            deepLinkAdapterListeners.onDeepLinkItemSelected(deepLinkVO)
        }

        ivDelete.setOnClickListenerWithDelay {
            deepLinkAdapterListeners.onDeleteDeepLink(deepLinkVO)
        }

        ivShare.setOnClickListenerWithDelay {
            itemView.context.shareTextOnIntent(deepLinkVO.deepLink)
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

    private fun MaterialButton.setCurrentFavoriteImage(isFavorite: Boolean) {
        setIconResource(
            if (isFavorite) {
                com.fabpps.theme.R.drawable.ic_favorite
            } else {
                com.fabpps.theme.R.drawable.ic_favorite_false
            }
        )
    }
}