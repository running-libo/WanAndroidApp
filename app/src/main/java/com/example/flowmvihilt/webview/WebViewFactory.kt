package com.example.flowmvihilt.webview

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.lifecycle.DefaultLifecycleObserver

object WebViewFactory: IWebView {

    override fun createWebView(webView: WebView): WebView {
        webView.setWebSettings()
        return webView
    }

    class WebAppView(context: Context) : WebView(context) {


    }

    override fun WebView.setWebSettings() {
        var isLoadPicMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("loadPic", true)
        this.settings.apply {
            javaScriptEnabled = true //支持javascript

            setSupportZoom(true) // 设置可以支持缩放
            builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
            displayZoomControls = false //隐藏屏幕中的虚拟缩放按钮

            textZoom = 100 //字体百分比，替代原API:setTextSize

            allowFileAccess = true //设置可以访问文件

            domStorageEnabled = true
            setSupportMultipleWindows(true)

            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN //自适应屏幕
            useWideViewPort = true //将图片调整到适合webview的大小
            loadWithOverviewMode = true // 缩放至屏幕的大小
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //优先使用缓存

            loadsImagesAutomatically = !isLoadPicMode!! //设置无图模式

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {  //开启夜间模式
                forceDark = WebSettings.FORCE_DARK_ON
            }

            //设置database缓存
            databaseEnabled = true
            var databaseDir = context.getDir("database", Context.MODE_PRIVATE).path
            databasePath = databaseDir
        }
    }

    override fun goBack() {

    }
}