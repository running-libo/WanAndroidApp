package com.example.flowmvihilt.base

import com.example.flowmvihilt.domain.entity.BaseData
import com.example.flowmvihilt.domain.entity.ReqState

open class BaseRepository {

    suspend fun <T : Any> executeRequest(block: suspend () -> BaseData<T>): BaseData<T> {
        val baseData = block.invoke()
        if (baseData.errorCode == 0) {
            //正确
            baseData.state = ReqState.Success
        } else {
            baseData.state = ReqState.Error
        }
        return baseData
    }
}