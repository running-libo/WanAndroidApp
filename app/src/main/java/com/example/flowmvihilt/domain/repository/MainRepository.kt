package com.example.flowmvihilt.domain.repository

import com.example.basemodule.entity.ArticleListData
import com.example.basemodule.entity.BaseData

interface MainRepository {

    suspend fun getArticals(page: Int): BaseData<ArticleListData>
}