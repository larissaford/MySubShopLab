package com.example.mysubshop

import android.app.Application
import timber.log.Timber

/**
 * sets up Timber logging application. doesn't require a tag and is a shorter log statement
 * "Timber" "Log" get it? :)
 */
class PusherApplication : Application() {

    /**
     * sets up Timber to be ready for use
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}