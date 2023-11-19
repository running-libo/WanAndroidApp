package com.example.flowmvihilt.domain.repository

import com.example.flowmvihilt.domain.entity.Photo

interface PhotoRepository {

    suspend fun getPhotos(
        start: Int,
        limit: Int
    ): List<Photo>
}