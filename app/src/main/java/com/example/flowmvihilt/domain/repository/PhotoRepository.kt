package com.example.flowmvihilt.domain.repository

import com.example.flowmvihilt.domain.entity.ArticleListData
import com.example.flowmvihilt.domain.entity.BaseData

interface PhotoRepository {

    suspend fun getPhotos(
        start: Int,
        limit: Int
    ): BaseData<ArticleListData>
}