package com.example.basemodule.basemvi

import androidx.lifecycle.MutableLiveData

/**
 * 处理分页加载ViewModel
 */
abstract class BasePageViewModel<UiState: IUiState, UiIntent: IUiIntent>: BaseViewModel<UiState, UiIntent>() {

    var curPage = MutableLiveData(0)

    private fun getStartPageNum(): Int = 0

    fun refresh(requestData: (page: Int) -> Unit) {
        curPage.value = getStartPageNum()

        requestData(getStartPageNum())
    }

    fun loadMore(requestData: (page: Int) -> Unit) {
        curPage.value = curPage.value?.plus(1)
        requestData(curPage.value!!)
    }
}