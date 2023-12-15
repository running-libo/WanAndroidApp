package com.example.flowmvihilt.system

import com.example.basemodule.base.IUiState

data class SysUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: List<SystemData>): DetailUiState()
}