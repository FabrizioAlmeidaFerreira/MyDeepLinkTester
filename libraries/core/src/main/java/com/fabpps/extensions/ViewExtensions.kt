package com.fabpps.extensions

import android.os.SystemClock
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView


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

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener.invoke(newText.orEmpty())
            return true
        }

    })
}