package com.example.flowmvihilt.system

import com.example.basemodule.basemvi.IUiState
import com.example.basemodule.entity.SystemData

data class SysUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: List<SystemData>): DetailUiState()
}