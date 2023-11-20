package com.example.flowmvihilt.data.remote

import com.example.flowmvihilt.domain.entity.ArticleListData
import com.example.flowmvihilt.domain.entity.BaseData
import com.example.flowmvihilt.network.Api
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiService {

  /**
   * 首页
   */
  @GET(Api.HOME_PAGE)
  suspend fun getHomePageData(@Path("page") page: Int): BaseData<ArticleListData>

  companion object {
    operator fun invoke(retrofit: Retrofit) = retrofit.create<MainApiService>()
  }
}