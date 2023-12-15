package com.example.basemodule.util

import android.util.Log

/**
 * create by libo
 * create on 2021/6/29
 * description  日志打印工具类
 */
object LogUtils {
    private var isOpenLog = true
    const val TAG = "commonTag"
    fun isOpenLog(isDebug: Boolean) {
        isOpenLog = isDebug
    }

    fun d(content: String) {
        if (!isOpenLog) return
        Log.d(TAG, content)
    }

    fun d(tag:()->String, content: String) {
        if (!isOpenLog) return
        Log.d(tag(), content)
    }

    fun e(content: String) {
        if (!isOpenLog) return
        Log.e(TAG, content)
    }

    fun e(tag:()->String, content: String) {
        if (!isOpenLog) return
        Log.d(tag(), content)
    }

    fun i(content: String) {
        if (!isOpenLog) return
        Log.i(TAG, content)
    }

    fun i(tag:()->String, content: String) {
        if (!isOpenLog) return
        Log.d(tag(), content)
    }

    fun v(content: String) {
        if (!isOpenLog) return
        Log.v(TAG, content)
    }

    fun v(tag:()->String, content: String) {
        if (!isOpenLog) return
        Log.d(tag(), content)
    }

    fun w(content: String) {
        if (!isOpenLog) return
        Log.w(TAG, content)
    }

    fun w(tag:()->String, content: String) {
        if (!isOpenLog) return
        Log.d(tag(), content)
    }
}