package com.example.flowmvihilt.application

import android.app.Application
import com.example.basemodule.util.ActivityLifecycleCallBack
import javax.inject.Inject

/**
 * 监听Activity生命周期事件
 */
class ActivityLifecycleTask @Inject constructor(val context: Application, private val activityLifecycle: ActivityLifecycleCallBack): Runnable {
    override fun run() {
        context.registerActivityLifecycleCallbacks(activityLifecycle)
    }
}