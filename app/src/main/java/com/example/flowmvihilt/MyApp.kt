package com.example.flowmvihilt

import com.example.basemodule.BuildConfig
import com.example.basemodule.application.BaseApplication
import com.example.basemodule.util.ActivityLifecycleCallBack
import com.example.basemodule.util.LanguageHelper
import com.example.basemodule.util.LogUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp: BaseApplication() {
    @Inject
    lateinit var activityLifecycle: ActivityLifecycleCallBack

    override fun onCreate() {
        super.onCreate()

        isOpenLog()
        registerActivity()
        LanguageHelper.getAttachBaseContext(this)
    }

    /**
     * 设置日志开关业务
     */
    private fun isOpenLog() {
        LogUtils.isOpenLog(BuildConfig.DEBUG)
    }

    /**
     * 监听Activity生命周期事件
     */
    private fun registerActivity() {
        registerActivityLifecycleCallbacks(activityLifecycle)
    }
}