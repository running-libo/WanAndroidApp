package com.example.flowmvihilt.webview

import android.webkit.WebView

interface IWebView {

    fun createWebView(webView: WebView): WebView

    fun WebView.setWebSettings()

    fun goBack()
}