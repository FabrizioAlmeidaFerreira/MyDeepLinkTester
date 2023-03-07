package com.fabpps.mydeeplinktester.features

import android.app.Application
import com.fabpps.deeplinkexecutor.init.DeepLinkFeature
import com.fabpps.deeplinkexecutor.init.FeatureParams

fun initFeaturesModules(
    application: Application
) {

    initDeepLinkExecutor(application)
}

fun initDeepLinkExecutor(application: Application) {
    DeepLinkFeature.init(
        params = FeatureParams(application)
    )
}
