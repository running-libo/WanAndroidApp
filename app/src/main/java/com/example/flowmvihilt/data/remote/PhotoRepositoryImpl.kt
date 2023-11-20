package com.example.flowmvihilt.data.remote

import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.domain.entity.ArticleListData
import com.example.flowmvihilt.domain.entity.BaseData
import com.example.flowmvihilt.domain.repository.PhotoRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * photo的网络请求方法
 */
@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,  //这个apiService不用创建，直接放到构造方法就能自动创建
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : PhotoRepository {

    override suspend fun getPhotos(start: Int, limit: Int): BaseData<ArticleListData> {
        return withContext(dispatchersProvider.io) {
            apiService.getHomePageData(start)
        }
    }

}