package com.example.flowmvihilt.webview

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.basemodule.constants.Tags
import com.example.basemodule.util.LogUtils
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * WebViewClient主要用来处理请求和加载页面监听
 */
class WanWebViewClient @Inject constructor(@ActivityContext val context: Context) : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        //用户可选择是否拦截加载 URL
        //如果返回值为 true，拦截 WebView 加载 url，false 允许 WebView 加载 url
        //所以在实际项目中，可以在这里处理自定义的一些跳转协议。
        LogUtils.d({ Tags.WEBVIEW }, "当前url: $url")

        if (url == null) {
            return false
        }
        if (url.startsWith("weixin://") || url.startsWith("jianshu://")) { //根据前缀通过schema跳转微信或简书
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
            return true
        }
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        //开始载入页面调用的
        LogUtils.d({ Tags.WEBVIEW }, "页面加载开始")
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        //在页面加载结束时调用
        LogUtils.d({ Tags.WEBVIEW }, "当前加载结束")
        super.onPageFinished(view, url)
    }

    override fun onLoadResource(view: WebView, url: String) {
        //在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
        super.onLoadResource(view, url)
    }

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        //App里面使用webview控件的时候遇到了诸如404这类的错误的时候，若也显示浏览器里面的那种错误提示页面就显得很丑陋了，那么这个时候我们的app就需要加载一个本地的错误提示页面
        // view.loadUrl("file:///android_assets/error_handle.html");
        super.onReceivedError(view, request, error)

        LogUtils.e({Tags.WEBVIEW}, "webview onReceivedError: $error")
    }

    override fun onReceivedHttpError(
        view: WebView,
        request: WebResourceRequest,
        errorResponse: WebResourceResponse
    ) {
        //响应服务器返回的 Http 错误，当一个 http 正常响应时，状态码会是 200，当状态码异常时可以用该方法监听。
        val code = errorResponse.statusCode
        when (code) {
            400 -> {}
        }
        super.onReceivedHttpError(view, request, errorResponse)

        LogUtils.e({Tags.WEBVIEW}, "webview onReceivedError: $errorResponse")
    }

    /**
     * Ssl 错误处理，由用户选择继续加载还是取消加载
     */
    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        LogUtils.e({Tags.WEBVIEW}, "webview onReceivedSslError: ${error.toString()}")

        //SSL 证书验证错误
        if (error.primaryError == SslError.SSL_EXPIRED || error.primaryError == SslError.SSL_DATE_INVALID) {
            handler.proceed()
        }

        AlertDialog.Builder(context)
            .setTitle("安全警告")
            .setMessage("当前出现Ssl Error")
            .setIcon(R.drawable.ic_dialog_alert)
            .setPositiveButton("继续加载页面"
            ) { dialog, which ->
                dialog?.dismiss()
                handler.proceed()
            }
            .setNegativeButton("取消加载页面"
            ) { dialog, which ->
                dialog?.dismiss()
                handler.cancel()
            }.show()
    }
}