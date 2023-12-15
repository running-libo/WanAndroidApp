package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.QuestionBean

interface QaRepository {

    suspend fun getQaData(): BaseData<QuestionBean>
}