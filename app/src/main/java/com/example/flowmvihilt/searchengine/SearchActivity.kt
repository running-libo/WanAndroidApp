package com.example.flowmvihilt.searchengine

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import com.example.basemodule.basemvi.BaseBindingActivity
import com.example.flowmvihilt.databinding.ActivitySearchBinding
import com.example.flowmvihilt.webview.WebViewActivity.Companion.gotoWebView

class SearchActivity : BaseBindingActivity<ActivitySearchBinding>({
    ActivitySearchBinding.inflate(it)
}) {

    private val searchViewModel by viewModels<SearchViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvGoSearch.setOnClickListener {
//            gotoWebView("https://www.bing.com/search?q=哈哈")
            var key = binding.etSearch.text.toString().trim()
            if (!TextUtils.isEmpty(key)) {
                gotoWebView(searchViewModel.getCurSearchUrl(key))
            }
        }

        binding.tvEngine.setOnClickListener {
            binding.tvEngine.text = searchViewModel.getNextEngine().name
        }

    }

}