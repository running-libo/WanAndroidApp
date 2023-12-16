package com.example.flowmvihilt.webview

import android.net.Uri
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.example.basemodule.constants.Tags.Companion.WEBVIEW
import com.example.basemodule.util.LogUtils
import javax.inject.Inject

/**
 * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站标题、加载进度等。
 */
class WanWebChromeClient @Inject constructor(val curProgress: (newProgress: Int) -> Unit) : WebChromeClient() {
    override fun onProgressChanged(view: WebView, newProgress: Int) {
        //这个方法会在网页加载过程中多次触发，当 newProgress = 100 时，可以认为网页加载完成。这个方法比 onPageFinished 更为准确，一般用来实现自定义进度条加载。
        LogUtils.d({WEBVIEW}, "当前加载进度： $newProgress")
        curProgress(newProgress)
        super.onProgressChanged(view, newProgress)
    }

    override fun onReceivedTitle(view: WebView, title: String) {
        //显示页面标题 title为该页面标题
        LogUtils.d({WEBVIEW}, "页面title： $title")
        super.onReceivedTitle(view, title)
    }

    /**
     * 页面提示框
     * @param message alert 弹出窗口中的提示信息（提示或警告信息对话框，仅一个确认按钮）
     * @param result 向网页中的 Javascript 代码反馈本次操作结果(result.confirm 代表点击了确定按钮，result.cancel 代表点击了取消按钮)
     */
    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        //页面提示框 onJsAlert()
        return super.onJsAlert(view, url, message, result)
    }

    /**
     * 页面选择框
     * @param message confirm 弹出窗口中的提示信息（确认对话框，有确认、取消两个按钮）
     * @param result 向网页中的 Javascript 代码反馈本次操作结果(result.confirm 代表点击了确定按钮，result.cancel 代表点击了取消按钮)
     */
    override fun onJsConfirm(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }

    /**
     * 选择文件
     */
    override fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        return true
    }
}