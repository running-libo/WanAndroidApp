package com.example.flowmvihilt.webview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.basemvi.BaseBindingActivity
import com.example.basemodule.entity.DataX
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(URL_KEY)

        initWebView()
    }

    companion object {
        private const val URL_KEY = "url"
        fun Activity.gotoWebView(url: String) {
            startActivity(Intent(this, WebViewActivity::class.java).apply {
                putExtra(URL_KEY, url)
            })
        }
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
                    }
                })
        }
        url?.let {
            binding.webview.loadUrl(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()


    }
}