package com.example.flowmvihilt.mine

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowmvihilt.widget.GoogleSearchWidget
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeViewModel @Inject constructor(@ApplicationContext val context: Context) : ViewModel() {

    /**
     * 切换深色模式
     */
    fun switchNightMode(isChecked: Boolean) {
        AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    /**
     * 动态向桌面添加widget
     */
    fun createDeskTopWidget() {
        val serviceComponent = ComponentName(context, GoogleSearchWidget::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (AppWidgetManager.getInstance(context).isRequestPinAppWidgetSupported) {
                AppWidgetManager.getInstance(context).requestPinAppWidget(serviceComponent, null, null)
                viewModelScope.launch {
                    //检测是否添加成功
                    delay(500)
                    val appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(serviceComponent)
                    if (appWidgetIds.isNotEmpty()) {
                        Toast.makeText(context, "桌面Widget添加成功", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "请先在应用权限页打开桌面快捷方式权限", Toast.LENGTH_SHORT).show()
            }
        }
    }
}