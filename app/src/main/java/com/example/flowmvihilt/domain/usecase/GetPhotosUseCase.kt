package com.example.flowmvihilt.domain.usecase

import com.example.flowmvihilt.domain.entity.Photo
import com.example.flowmvihilt.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(start: Int, limit: Int): List<Photo> {
        return photoRepository.getPhotos(start = start, limit = limit)
    }
}