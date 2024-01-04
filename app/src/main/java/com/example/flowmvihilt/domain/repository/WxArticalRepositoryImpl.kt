package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.basemodule.entity.WxArticalResponse
import com.example.flowmvihilt.data.remote.IWxArticalService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WxArticalRepositoryImpl @Inject constructor(
    private val apiService: IWxArticalService,
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : WxArticalRepository {

    override suspend fun getData(): BaseData<List<WxArticalResponse>> {
        return withContext(dispatchersProvider.io) {
            apiService.getWxArticalData()
        }
    }

}