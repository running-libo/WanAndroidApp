package com.example.flowmvihilt.system

import com.example.basemodule.base.BaseViewModel
import com.example.basemodule.base.IUiIntent
import com.example.flowmvihilt.domain.repository.SysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SysViewModel @Inject constructor(val repository: SysRepository) : BaseViewModel<SysUiState, SysIntent>() {
    override fun initUiState(): SysUiState {
        return SysUiState(DetailUiState.INIT)
    }

    override fun handleIntent(intent: IUiIntent) {
        when(intent) {
            is SysIntent.getDatas -> {
                requestDataWithFlow(true,
                    request = { repository.getSysData()},
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

}