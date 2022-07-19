package com.challenge.repos

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Mahmoud Hamwi on 17-Jul-22.
 */

@HiltAndroidApp
class App : MultiDexApplication() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun getInstance(): Context {
            return instance!!.applicationContext
        }
    }
}