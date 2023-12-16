package com.example.flowmvihilt.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.flowmvihilt.R
import com.example.flowmvihilt.widget.GoogleSearchWidget.Companion.ACTION_LAUNCHER

/**
 * Implementation of App Widget functionality.
 */
class GoogleSearchWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        //此方法可以按 AppWidgetProviderInfo 中的 updatePeriodMillis 属性定义的时间间隔来更新应用微件
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // 首次创建应用微件的实例时，会调用此方法。
    }

    override fun onDisabled(context: Context) {
        // 从应用微件托管应用中删除了应用微件的最后一个实例时，会调用此方法。
    }

    companion object {
        const val ACTION_LAUNCHER = "com.wanandroid.launcher"
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.wanandroid_widget)

    //点击view跳转到指定action
    views.setOnClickPendingIntent(R.id.ll_widget_bg, getPendingIntentByAction(context, appWidgetId, ACTION_LAUNCHER))

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getPendingIntentByAction(context: Context, appWidgetId: Int, action: String): PendingIntent {
    with(Intent()) {
        this.action = action
        //使用FLAG_ACTIVITY_CLEAR_TOP会将该启动的activity上层activity弹出栈，确保该activity能显示在顶层
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        var flag = PendingIntent.FLAG_UPDATE_CURRENT
        flag = flag or PendingIntent.FLAG_IMMUTABLE
        return PendingIntent.getActivity(
            context,
            appWidgetId,
            this,
            flag
        )
    }
}