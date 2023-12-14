package com.example.basemodule.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basemodule.entity.BaseData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState: IUiState, UiIntent: IUiIntent>: ViewModel() {

    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    private val uiIntentFlow: Flow<UiIntent> = _uiIntentFlow.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    private val _loadUiIntentFlow: Channel<LoadUiIntent> = Channel()
    val loadUiIntentFlow: Flow<LoadUiIntent> = _loadUiIntentFlow.receiveAsFlow()

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

    /**
     * 发送当前加载状态：Loading、Error、Normal
     */
    private fun sendLoadUiIntent(loadUiIntent: LoadUiIntent) {
        viewModelScope.launch {
            _loadUiIntentFlow.send(loadUiIntent)
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
            sendLoadUiIntent(LoadUiIntent.Error(errMsg))
        }
    ) {
        viewModelScope.launch {
            if (showLoading) {
                sendLoadUiIntent(LoadUiIntent.Loading(true))
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
                    sendLoadUiIntent(LoadUiIntent.Loading(false))
                }
            }
        }
    }

}