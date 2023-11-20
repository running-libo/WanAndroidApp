package com.example.flowmvihilt.ui.main

import com.example.flowmvihilt.base.BaseViewModel
import com.example.flowmvihilt.base.DetailUiState
import com.example.flowmvihilt.base.IUiIntent
import com.example.flowmvihilt.base.MainUiState
import com.example.flowmvihilt.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(val photoRepository: MainRepository) : BaseViewModel<MainUiState, MainIntent>() {

    override fun handleIntent(intent: IUiIntent) {
        when(intent) {
            is MainIntent.getDetail -> {
                requestDataWithFlow(
                    showLoading = true,
                    request = { photoRepository.getArticals(0) },
                    successCallBack = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.SUCCESS(data)
                            )
                        }
                    })
                }
            }
        }

    override fun initUiState(): MainUiState {
        return MainUiState(DetailUiState.INIT)
    }

}