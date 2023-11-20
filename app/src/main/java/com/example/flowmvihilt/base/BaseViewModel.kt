package com.example.flowmvihilt.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowmvihilt.domain.entity.BaseData
import com.example.flowmvihilt.domain.entity.ReqState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState: IUiState, UiIntent: IUiIntent>: ViewModel() {

    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    protected abstract fun initUiState(): UiState

    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(uiIntent)
        }
    }

    fun sendUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update { copy(_uiStateFlow.value) }
    }

    protected abstract fun handleIntent(intent: IUiIntent)

    protected fun <T: Any> requestDataWithFlow(
        showLoading: Boolean = true,
        request: suspend () -> BaseData<T>,
        successCallBack: (T) -> Unit,
        failCallBack: suspend (String) -> Unit = { errMsg->

        }
    ) {
        viewModelScope.launch {
            if (showLoading) {

            }
            try {
                val baseData = request.invoke()
                if (baseData.isSuccess()) {
                    baseData.data?.let { successCallBack(it) }
                } else {
                    baseData.errorMsg?.let { error(it) }
                }
            } catch (e: Exception) {
                e.message?.let { failCallBack(it) }
            } finally {
                if (showLoading) {

                }
            }
        }
    }

}