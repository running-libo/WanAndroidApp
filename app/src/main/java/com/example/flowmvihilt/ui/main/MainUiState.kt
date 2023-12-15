package com.example.flowmvihilt.ui.main

import com.example.basemodule.basemvi.IUiState
import com.example.basemodule.entity.ArticleListData
import com.example.basemodule.entity.BannerData

/**
 * 首页UI状态
 */
data class MainUiState(val detailUiState: DetailUiState, val bannerUiState: BannerUiState): IUiState

/**
 * 主页面UI状态
 */
sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val page: Int, val data: ArticleListData): DetailUiState()
}

/**
 * Banner UI状态
 */
sealed class BannerUiState {
    object INIT: BannerUiState()
    data class SUCCESS(val data: ArrayList<BannerData>): BannerUiState()
}