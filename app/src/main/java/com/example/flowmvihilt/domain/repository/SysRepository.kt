package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.flowmvihilt.system.SystemData

interface SysRepository {

    suspend fun getSysData(): BaseData<List<SystemData>>
}