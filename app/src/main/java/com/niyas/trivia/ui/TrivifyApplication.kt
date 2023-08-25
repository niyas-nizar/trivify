package com.niyas.trivia.ui

import android.app.Application
import timber.log.Timber

class TrivifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}