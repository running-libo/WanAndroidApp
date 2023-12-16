package com.example.flowmvihilt.application

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils
import com.example.basemodule.application.BaseApplication
import com.example.basemodule.util.TaskDispatcher
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp: BaseApplication() {
    @Inject
    lateinit var logInitTask: LogInitTask
    @Inject
    lateinit var activityLifecycleTask: ActivityLifecycleTask
    @Inject
    lateinit var webviewInitTask: WebviewInitTask
    @Inject
    lateinit var languageInitTask: LanguageInitTask

    override fun onCreate() {
        super.onCreate()

        if (!isMainProcess(applicationContext)) { //只允许主进程走app初始化
            return
        }

        TaskDispatcher()
            .addTask(logInitTask)
            .addTask(activityLifecycleTask)
            .addTask(webviewInitTask)
            .addTask(languageInitTask)
            .start()
    }
}