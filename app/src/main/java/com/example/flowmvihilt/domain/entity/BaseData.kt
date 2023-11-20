package com.example.flowmvihilt.domain.entity

class BaseData<T> {
    var errorCode = -1
    var errorMsg: String? = null
    var data: T? = null
    var state: ReqState = ReqState.Error

    override fun toString(): String {
        return "BaseData(code=$errorCode, msg=$errorMsg, data=$data, state=$state)"
    }

    companion object {
        const val CODE_SUCCESS = 0
    }

    fun isSuccess(): Boolean = errorCode == CODE_SUCCESS
}

enum class ReqState {
    Success, Error
}