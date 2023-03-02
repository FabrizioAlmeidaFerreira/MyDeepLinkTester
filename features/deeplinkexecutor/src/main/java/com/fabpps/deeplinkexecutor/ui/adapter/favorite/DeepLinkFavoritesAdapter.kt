package com.fabpps.deeplinkexecutor.ui.adapter.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabpps.data.dto.DeepLinkVO
import com.fabpps.deeplinkexecutor.R
import com.fabpps.deeplinkexecutor.domain.interfaces.DeepLinkAdapterListeners

class DeepLinkFavoritesAdapter : RecyclerView.Adapter<DeepLinkFavoriteViewHolder>() {

    private var oldDeepLinkList = mutableListOf<DeepLinkVO>()
    private lateinit var deepLinkAdapterListeners: DeepLinkAdapterListeners

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeepLinkFavoriteViewHolder {
        return DeepLinkFavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.deep_link_item,
                parent,
                false
            ),
            deepLinkAdapterListeners
        )
    }

    override fun onBindViewHolder(holder: DeepLinkFavoriteViewHolder, position: Int) {
        holder.binding(oldDeepLinkList[position])
    }

    override fun getItemCount(): Int = oldDeepLinkList.count()

    fun setListeners(deepLinkAdapterListeners: DeepLinkAdapterListeners) {
        this.deepLinkAdapterListeners = deepLinkAdapterListeners
    }

    fun setItemsList(newDeepList: MutableList<DeepLinkVO>) {
        val deepLinDiffUtil = DeepLinkAdapterDiffUtil(oldDeepLinkList, newDeepList)
        val diffUtilsResult = DiffUtil.calculateDiff(deepLinDiffUtil)
        oldDeepLinkList = newDeepList
        diffUtilsResult.dispatchUpdatesTo(this)
    }
}