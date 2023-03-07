package com.fabpps.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.nonNullObserver(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner) {
        it?.let(observer)
    }
}