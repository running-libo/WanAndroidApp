package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.WxArticalResponse

interface WxArticalRepository {

    suspend fun getData(): BaseData<List<WxArticalResponse>>
}