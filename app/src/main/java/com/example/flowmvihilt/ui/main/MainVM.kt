package com.example.flowmvihilt.ui.main

import com.example.basemodule.basemvi.BasePageViewModel
import com.example.basemodule.basemvi.IUiIntent
import com.example.basemodule.entity.BannerData
import com.example.flowmvihilt.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val mainRepository: MainRepository) : BasePageViewModel<MainUiState, MainIntent>() {

    override fun handleIntent(intent: IUiIntent) {
            when(intent) {
                is MainIntent.getDetail -> {
                    requestDataWithFlow(
                        showLoading = true,
                        request = { mainRepository.getArticals(0) },
                        successCallBack = { data ->
                            sendUiState {
                                copy(
                                    detailUiState = DetailUiState.SUCCESS(data)
                                )
                            }
                        })
                }
                is MainIntent.getBanner -> {
                    requestDataWithFlow(true,
                        request = { mainRepository.getBanner() },
                        successCallBack = { data ->
                            sendUiState {
                                copy(
                                    bannerUiState = BannerUiState.SUCCESS(data as ArrayList<BannerData>)
                                )
                            }
                        })
                }
            }
        }

    override fun initUiState(): MainUiState {
        return MainUiState(DetailUiState.INIT, BannerUiState.INIT)
    }

}