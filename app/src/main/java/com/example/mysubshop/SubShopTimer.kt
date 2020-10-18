package com.example.mysubshop

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class SubShopTimer(lifecycle: Lifecycle): LifecycleObserver {
    //observe main activities life cycle
    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startTimer() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopTimer() {

    }
}