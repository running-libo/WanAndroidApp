package com.example.flowmvihilt.application

import com.example.basemodule.BuildConfig
import com.example.basemodule.util.LogUtils
import javax.inject.Inject

/**
 * 设置日志开关业务
 */
class LogInitTask @Inject constructor() : Runnable {
    override fun run() {
        LogUtils.isOpenLog(BuildConfig.DEBUG)
    }
}