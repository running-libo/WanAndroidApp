package com.example.flowmvihilt.ui.main

import com.example.basemodule.base.IUiState
import com.example.flowmvihilt.domain.entity.ArticleListData

data class MainUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: ArticleListData): DetailUiState()
}