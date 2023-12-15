package com.example.flowmvihilt.data.remote

import com.example.basemodule.entity.ArticleListData
import com.example.basemodule.entity.BannerData
import com.example.basemodule.entity.BaseData
import com.example.basemodule.network.Api
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

  /**
   * banner
   */
  @GET(Api.BANNER)
  suspend fun getBannerData(): BaseData<List<BannerData>>

  companion object {
    operator fun invoke(retrofit: Retrofit) = retrofit.create<MainApiService>()
  }
}
