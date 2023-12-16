package com.example.flowmvihilt.widget

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.flowmvihilt.R
import java.util.concurrent.TimeUnit

/**
 * 通知栏
 */
class NotificationCard(val context: Context) {
    companion object {
        private const val TAG_NOTIFICATION = "tag_notification"

        /**
         * 启动周期调起通知栏任务
         */
        fun startNotificationWork(context: Context) {
        WorkManager.getInstance(context).cancelAllWorkByTag(TAG_NOTIFICATION)
            val periodicWorkRequest = PeriodicWorkRequest
                .Builder(NotificationWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag(TAG_NOTIFICATION)
                .build()
            WorkManager.getInstance(context).enqueue(periodicWorkRequest)
        }
    }


    //通知Id
    private val permanentNotificationId = 4
    private var notificationManager: NotificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
    private var builder: NotificationCompat.Builder = NotificationCompat.Builder(context)

    init {
        var remoteView = RemoteViews(context.packageName, R.layout.layout_notify_search)
        builder?.setSmallIcon(com.example.resmodule.R.mipmap.ic_launcher)
        builder?.setContent(remoteView)
        addChannelForNotificationBuilder(notificationManager, builder, "searchGroup", "search", "searchid", "search")
    }

    fun showNotify() {
        notificationManager.notify(permanentNotificationId, builder?.build())
    }


    private fun addChannelForNotificationBuilder(
        notificationManager: NotificationManager,
        build: NotificationCompat.Builder?,
        groupID: String?,
        groupName: String?,
        channelID: String,
        channelName: CharSequence?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val group = NotificationChannelGroup(groupID, groupName)
            notificationManager.createNotificationChannelGroup(group)
            val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.group = groupID
            channel.setShowBadge(false)
            channel.enableLights(false)
            channel.setSound(null, null)
            channel.enableVibration(false)
            channel.vibrationPattern = longArrayOf(0)
            channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
            notificationManager.createNotificationChannel(channel)
            build?.setChannelId(channelID)
        }
    }
}