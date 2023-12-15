package com.example.flowmvihilt.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.basemodule.base.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WevViewFragment : BaseBindingFragment<FragmentWebviewBinding>({
    FragmentWebviewBinding.inflate(it)
}) {
    private var url: String?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        url = arguments?.getString("url")
        setSetting()
        initWebView()
    }

    private fun setSetting() {
        binding.webview.settings.apply {
            //支持javascript
            javaScriptEnabled = true
            // 设置可以支持缩放
            setSupportZoom(true)
            useWideViewPort = true
            //自适应屏幕
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            loadWithOverviewMode = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

    }

    private fun initWebView() {
        binding.webview.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(webview: WebView, url: String): Boolean {
                if (url == null) {
                    return false
                }

                if (url.startsWith("weixin://") || url.startsWith("jianshu://")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                webview.loadUrl(url)
                return true
            }
        }
        binding.webview.webChromeClient = object: WebChromeClient() {
            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)


            }
        }
        url?.let { binding.webview.loadUrl(it) }
    }

//    override fun onDestroy() {
//        binding?.let {
//            with(it.webview) {
//                loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
//                clearHistory()
//                (parent as ViewGroup).removeView(this)
//                destroy()
//            }
//        }
//        super.onDestroy()
//    }
}