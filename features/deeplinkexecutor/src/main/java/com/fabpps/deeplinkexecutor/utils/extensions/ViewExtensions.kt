package com.fabpps.deeplinkexecutor.utils.extensions

import android.os.SystemClock
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText


const val DELAYED_CLICK_TIME = 600L

fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            return@setOnEditorActionListener true
        }
        false
    }
}

fun View.setOnClickListenerWithDelay(delay: Long = DELAYED_CLICK_TIME, listener: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickedTime: Long = 0
        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickedTime < delay) return
            else listener()
            lastClickedTime = SystemClock.elapsedRealtime()
        }
    })
}