package com.example.flowmvihilt.ui.main

import androidx.lifecycle.viewModelScope
import com.example.flowmvihilt.base.BaseViewModel
import com.example.flowmvihilt.base.DetailUiState
import com.example.flowmvihilt.base.IUiIntent
import com.example.flowmvihilt.base.MainUiState
import com.example.flowmvihilt.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(val photoRepository: PhotoRepository) : BaseViewModel<MainUiState, MainIntent>() {

    override fun handleIntent(intent: IUiIntent) {
        when(intent) {
            is MainIntent.getDetail -> {
                viewModelScope.launch {
                    val photo = photoRepository.getPhotos(0, 20)
                    //数据请求成功，发送加载页面state
                    sendUiState {
                        copy(
                            detailUiState = DetailUiState.SUCCESS(photo)
                        )
                    }
                }
            }
        }
    }

    override fun initUiState(): MainUiState {
        return MainUiState(DetailUiState.INIT)
    }
}