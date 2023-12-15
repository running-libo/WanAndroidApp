package com.example.flowmvihilt

import com.example.basemodule.application.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: BaseApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}