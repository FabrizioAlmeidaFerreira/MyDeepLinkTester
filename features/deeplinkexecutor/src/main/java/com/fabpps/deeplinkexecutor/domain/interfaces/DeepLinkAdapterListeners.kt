package com.fabpps.deeplinkexecutor.domain.interfaces

import com.fabpps.data.dto.DeepLinkVO

interface DeepLinkAdapterListeners {
    fun onDeepLinkItemSelected(deepLinkVO: DeepLinkVO)
    fun onDeepLinkUpdate(deepLinkVO: DeepLinkVO)
    fun onDeleteDeepLink(deepLinkVO: DeepLinkVO)
}