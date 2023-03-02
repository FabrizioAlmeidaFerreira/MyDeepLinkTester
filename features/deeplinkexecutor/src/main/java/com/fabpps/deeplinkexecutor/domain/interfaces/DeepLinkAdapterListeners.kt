package com.fabpps.deeplinkexecutor.domain.interfaces

import com.fabpps.data.dto.DeepLinkVO

interface DeepLinkAdapterListeners {
    fun onDeepLinkItemSelected(deepLinkVO: DeepLinkVO)
}