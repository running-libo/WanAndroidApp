package com.example.flowmvihilt.application

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

        TaskDispatcher()
            .addTask(logInitTask)
            .addTask(activityLifecycleTask)
            .addTask(webviewInitTask)
            .addTask(languageInitTask)
            .start()
    }
}