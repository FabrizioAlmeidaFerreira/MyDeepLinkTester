package com.fabpps.deeplinkexecutor.init

import com.fabpps.deeplinkexecutor.di.FeatureKoinContext

object DeepLinkFeature {

    fun init(params: FeatureParams) {
        FeatureKoinContext.initialize(params)
    }
}