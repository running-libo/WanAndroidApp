package com.example.flowmvihilt.gongzhonghao

import com.example.basemodule.basemvi.IUiState
import com.example.basemodule.entity.WxArticalResponse

data class WxArticalUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: List<WxArticalResponse>): DetailUiState()
}