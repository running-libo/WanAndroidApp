package com.example.flowmvihilt.qa

import com.example.basemodule.basemvi.IUiState
import com.example.basemodule.entity.DataBean

data class QaUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: List<DataBean>): DetailUiState()
}