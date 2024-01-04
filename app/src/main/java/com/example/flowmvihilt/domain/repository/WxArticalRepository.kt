package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.WxArticalList
import com.example.basemodule.entity.WxArticalResponse

interface WxArticalRepository {

    suspend fun getData(): BaseData<List<WxArticalResponse>>

    suspend fun getDataList(id: Int, page: Int): BaseData<WxArticalList>
}