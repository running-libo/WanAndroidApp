package com.example.flowmvihilt.webview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.basemvi.BaseBindingActivity
import com.example.flowmvihilt.databinding.ActivityWebviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WebViewActivity : BaseBindingActivity<ActivityWebviewBinding>({
    ActivityWebviewBinding.inflate(it)
}) {
    @Inject
    lateinit var wanWebViewClient: WanWebViewClient
    private var url: String?= null

    companion object {
        private const val URL_KEY = "url"
        fun Activity.gotoWebView(url: String) {
            startActivity(Intent(this, WebViewActivity::class.java).apply {
                putExtra(URL_KEY, url)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(URL_KEY)

        initWebView()
        initSmartRefresh()
    }

    private fun initWebView() {
        WebViewFactory.createWebView(binding.webview)
        with(binding.webview) {
            webViewClient = wanWebViewClient
            webChromeClient = WanWebChromeClient(
                loadProgress = {
                    binding.progressbar.progress = it //webview进度条更新
                }, loadFinish = {
                    lifecycleScope.launch {
                        delay(100)
                        binding.progressbar.visibility = android.view.View.GONE  //隐藏webview进度条

                        binding.smartRefreshLayout?.finishRefresh()
                    }
                })
        }
        url?.let {
            binding.webview.loadUrl(it)
        }
    }

    private fun initSmartRefresh() {
        binding.smartRefreshLayout.setOnRefreshListener {
            binding.webview.reload()
        }
    }

    override fun onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {

        binding?.webview?.let {
            it.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            it.clearHistory()
            if (parent != null) {
                (it.parent as ViewGroup).removeView(it)
            }
            it.destroy()
        }

        super.onDestroy()
    }
}