package com.fabpps.deeplinkexecutor.ui.adapter.favorite

import androidx.recyclerview.widget.DiffUtil
import com.fabpps.data.dto.DeepLinkVO

class DeepLinkAdapterDiffUtil (
    private val oldList: MutableList<DeepLinkVO>,
    private val newList: MutableList<DeepLinkVO>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.count()
    }

    override fun getNewListSize(): Int {
        return newList.count()
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].deepLinkIsFavorite == newList[newItemPosition].deepLinkIsFavorite
                && oldList[oldItemPosition].deepLink == newList[newItemPosition].deepLink
    }
}