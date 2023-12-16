package com.example.flowmvihilt.widget

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 用Workmanager执行周期性任务
 */
class NotificationWorker(val context: Context, params:WorkerParameters) : Worker(context,params) {

    override fun doWork(): Result {
        return try {
            NotificationCard(context).showNotify()
            Result.success()
        } catch (e:Exception){
            Result.failure()
        }
    }
}