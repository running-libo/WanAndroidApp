package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.QuestionBean
import com.example.flowmvihilt.data.remote.QaService
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QaRepositoryImpl @Inject constructor(
    private val apiService: QaService,
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : QaRepository {

    override suspend fun getQaData(): BaseData<QuestionBean> {
        return withContext(dispatchersProvider.io) {
            apiService.getQaData(0)
        }
    }

}