package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.flowmvihilt.data.remote.ISystemService
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.system.SystemData
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SysRepositoryImpl @Inject constructor(
    private val apiService: ISystemService,
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : SysRepository {

    override suspend fun getSysData(): BaseData<List<SystemData>> {
        return withContext(dispatchersProvider.io) {
            apiService.getSystemData()
        }
    }

}