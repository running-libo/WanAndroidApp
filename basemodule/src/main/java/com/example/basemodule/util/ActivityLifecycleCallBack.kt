package com.example.basemodule.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.basemodule.constants.Tags
import javax.inject.Inject

class ActivityLifecycleCallBack @Inject constructor(): Application.ActivityLifecycleCallbacks {
    private val tag = Tags.ACTIVITY

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        LogUtils.d({tag}, "onActivityCreated")
        ActivityManager.addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        LogUtils.d({tag}, "onActivityStarted")
    }

    override fun onActivityResumed(activity: Activity) {
        LogUtils.d({tag}, "onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        LogUtils.d({tag}, "onActivityPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        LogUtils.d({tag}, "onActivityStopped")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        LogUtils.d({tag}, "onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        LogUtils.d({tag}, "onActivityDestroyed")
        ActivityManager.removeActivity(activity)
    }
}