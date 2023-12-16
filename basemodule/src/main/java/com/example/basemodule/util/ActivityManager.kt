package com.example.basemodule.util

import android.app.Activity
import android.os.Process
import java.util.*
import kotlin.system.exitProcess

/**
 * create by libo
 * create on 2021/6/27
 * description Activity出入栈管理类
 */
object ActivityManager {
    /**
     * 用于存储和统一销毁一套操作的Activities
     */
    private var activities: Stack<Activity>? = null

    /**
     * 将创建的activity入栈管理
     *
     * @param activity
     */
    fun addActivity(activity: Activity) {
        if (activities == null) {
            activities = Stack()
        }
        activities?.add(activity)
    }

    /**
     * 将该activity出栈
     *
     * @param activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activities?.remove(activity)
            activity.finish()
        }
    }

    /**
     * 结束所有activity
     */
    fun finishAll() {
        activities?.forEach {
            if (!it.isFinishing) {
                it.finish()
            }
        }
        activities?.clear()
        Process.killProcess(Process.myPid())
    }

    /**
     * 退出程序并杀死任务栈
     */
    fun exitApp() {
        // 结束进程
        exitProcess(0)
    }
}