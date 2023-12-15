package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.SystemData

interface SysRepository {

    suspend fun getSysData(): BaseData<List<SystemData>>
}