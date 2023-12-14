package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.libo.modulesystem.SystemData

interface SysRepository {

    suspend fun getSysData(): BaseData<List<SystemData>>
}