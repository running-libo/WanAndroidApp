package com.example.flowmvihilt.domain.repository

import com.example.flowmvihilt.data.remote.MainApiService
import com.example.flowmvihilt.domain.dispatchers.CoroutinesDispatchersProvider
import com.example.flowmvihilt.domain.entity.ArticleListData
import com.example.flowmvihilt.domain.entity.BaseData
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Main的网络请求方法
 */
@Singleton
class MainRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,  //这个apiService不用创建，直接放到构造方法就能自动创建
    private val dispatchersProvider: CoroutinesDispatchersProvider
) : MainRepository {

    override suspend fun getArticals(page: Int): BaseData<ArticleListData> {
        return withContext(dispatchersProvider.io) {
            apiService.getHomePageData(page)
        }
    }

}