package com.fabpps.extensions

import android.content.Context
import android.content.Intent

fun Context.shareTextOnIntent(text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        putExtra(Intent.EXTRA_TITLE, "Compartilhar deep-link")
        type = "text/plain"
    }
    startActivity(Intent.createChooser(sendIntent, null))
}