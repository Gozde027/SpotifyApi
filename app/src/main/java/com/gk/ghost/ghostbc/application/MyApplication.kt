package com.gk.ghost.ghostbc.application

import android.app.Application
import com.gk.ghost.ghostbc.extension.PreferenceHelper

/**
 * Created by Gozde Kaval on 5/26/2018.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyApplication.prefHelper = PreferenceHelper(this)
    }

    companion object {
        lateinit var prefHelper: PreferenceHelper
            private set
    }
}
