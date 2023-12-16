package com.example.basemodule.util

import android.os.Looper
import android.os.MessageQueue.IdleHandler
import java.util.LinkedList
import java.util.Queue

/**
 * 应用异步初始化Task
 */
class TaskDispatcher {
    private val delayTasks: Queue<Runnable> = LinkedList()

    private val idleHandler = IdleHandler {
        if (delayTasks.size > 0) {
            val task = delayTasks.poll()
            task?.run()
        }
        !delayTasks.isEmpty() //只要task任务不为空，就继续执行初始化
    }

    fun addTask(task: Runnable): TaskDispatcher {
        delayTasks.add(task)
        return this
    }

    fun start() {
        Looper.myQueue().addIdleHandler(idleHandler)
    }
}