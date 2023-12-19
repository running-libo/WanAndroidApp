package com.example.flowmvihilt.searchengine

import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    private val searchEngines = ArrayList<SearchEnginBean>()
    private var curEnginIndex = 0

    init {
        with(searchEngines) {
            add(SearchEnginBean("Google", 0, "https://www.google.com/search?q=%s"))
            add(SearchEnginBean("YouTube", 0, "https://www.youtube.com/results?q=%s"))
            add(SearchEnginBean("Bing", 0, "https://www.bing.com/search?q=%s"))
            add(SearchEnginBean("百度", 0, "https://www.baidu.com/s?wd=%s"))
        }
    }

    fun getCurSearchUrl(key: String) = String.format(searchEngines[curEnginIndex].searchUrl, key.trim())

    fun getNextEngine(): SearchEnginBean {
        if (curEnginIndex < searchEngines.size-1) {
            curEnginIndex++
        } else {
            curEnginIndex = 0
        }
        return searchEngines[curEnginIndex]
    }
}