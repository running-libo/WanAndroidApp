package com.example.flowmvihilt.qa

import com.example.basemodule.base.BaseViewModel
import com.example.basemodule.base.IUiIntent
import com.example.flowmvihilt.domain.repository.QaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QaViewModel @Inject constructor(val repository: QaRepository) : BaseViewModel<QaUiState, QaIntent>() {
    override fun initUiState(): QaUiState {
        return QaUiState(DetailUiState.INIT)
    }

    override fun handleIntent(intent: IUiIntent) {
        when(intent) {
            is QaIntent.getDatas -> {
                requestDataWithFlow(true,
                    request = { repository.getQaData()},
                    successCallBack = { data ->
                        sendUiState {
                            copy(
                                detailUiState = DetailUiState.SUCCESS(data.datas)
                            )
                        }
                    })
            }
        }
    }

}