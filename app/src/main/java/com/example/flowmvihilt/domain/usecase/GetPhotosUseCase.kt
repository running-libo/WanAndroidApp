package com.example.flowmvihilt.domain.usecase

import com.example.flowmvihilt.data.remote.PhotoRepositoryImpl
import com.example.flowmvihilt.domain.entity.ArticleListData
import com.example.flowmvihilt.domain.entity.BaseData
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepositoryImpl
) {
    suspend operator fun invoke(start: Int, limit: Int): BaseData<ArticleListData> {
        return photoRepository.getPhotos(start = start, limit = limit)
    }
}