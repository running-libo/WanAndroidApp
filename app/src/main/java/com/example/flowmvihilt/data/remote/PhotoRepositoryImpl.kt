package com.example.flowmvihilt.data.remote

import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.domain.entity.Photo
import com.example.flowmvihilt.domain.repository.PhotoRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * photo的网络请求方法
 */
@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : PhotoRepository {

    override suspend fun getPhotos(start: Int, limit: Int): List<Photo> {
        return withContext(dispatchersProvider.io) {
            apiService.getPhotos(start, limit).map {
                Photo(
                    id = it.id,
                    title = it.title,
                    albumId = it.albumId,
                    thumbnailUrl = it.thumbnailUrl,
                    url = it.url
                )
            }
        }
    }

}