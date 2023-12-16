package com.example.basemodule.application

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.example.basemodule.constants.Tags
import com.example.basemodule.util.LogUtils

/**
 * create by libo
 * create on 2021/6/27
 * description
 */
open class BaseApplication : Application() {

    companion object {
        var instance: Application? = null
    }

    override fun onCreate() {
        LogUtils.d({ Tags.APPLICATION}, "application onCreate")
        super.onCreate()
    }

    override fun onTerminate() {
        // 程序终止的时候执行
        LogUtils.d({Tags.APPLICATION}, "onTerminate")
        super.onTerminate()
    }

    override fun onLowMemory() {
        // 低内存的时候执行
        LogUtils.d({Tags.APPLICATION}, "onLowMemory")
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        LogUtils.d({Tags.APPLICATION}, "onTrimMemory")
        super.onTrimMemory(level)
    }

    protected fun isMainProcess(context: Context): Boolean {
        try {
            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val processInfos = am.runningAppProcesses
            val mainProcessName = context.packageName
            val myPid = Process.myPid()
            if (processInfos == null) return false
            for (info in processInfos) {
                if (info.pid == myPid && mainProcessName == info.processName) {
                    return true
                }
            }
            return false
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }

}