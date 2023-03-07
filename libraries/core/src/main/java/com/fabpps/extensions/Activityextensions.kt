package com.fabpps.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.ParseException
import android.net.Uri

fun Activity.executeDeepLinkIntent(
    deepLink: String,
    actionOnExecute: () -> Unit,
    finish: Boolean,
    actionOnError: () -> Unit
) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)))
        actionOnExecute.invoke()
        if (finish) finish()
    } catch (e: ActivityNotFoundException) {
        actionOnError.invoke()
    } catch (e: ParseException) {
        actionOnError.invoke()
    }
}