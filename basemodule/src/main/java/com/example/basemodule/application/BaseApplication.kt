package com.example.basemodule.application

import android.app.Application
import android.util.Log
import com.example.basemodule.constants.Tags
import com.example.basemodule.util.LanguageHelper

/**
 * create by libo
 * create on 2021/6/27
 * description
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(Tags.APPLICATION, "onCreate")

        LanguageHelper.getAttachBaseContext(this)
    }

    companion object {
        var instance: Application? = null
    }

    override fun onTerminate() {
        // 程序终止的时候执行
        Log.d(Tags.APPLICATION, "onTerminate")
        super.onTerminate()
    }

    override fun onLowMemory() {
        // 低内存的时候执行
        Log.d(Tags.APPLICATION, "onLowMemory")
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.d(Tags.APPLICATION, "onTrimMemory")
        super.onTrimMemory(level)
    }

}