package com.example.flowmvihilt.base

import com.example.flowmvihilt.domain.entity.Photo

data class MainUiState(val detailUiState: DetailUiState): IUiState

sealed class DetailUiState {
    object INIT: DetailUiState()
    data class SUCCESS(val data: List<Photo>): DetailUiState()
}