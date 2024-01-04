package com.example.flowmvihilt.data.remote

import com.example.basemodule.entity.BaseData
import com.example.basemodule.network.Api
import com.example.basemodule.entity.WxArticalResponse
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

/**
 * create by libo
 * create on 2021/6/29
 * description
 */
interface IWxArticalService {

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<IWxArticalService>()
    }

    @GET(Api.WX_ARTICAL)
    suspend fun getWxArticalData(): BaseData<List<WxArticalResponse>>
}