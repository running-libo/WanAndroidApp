package com.example.flowmvihilt.data.remote

import com.example.basemodule.entity.BaseData
import com.example.basemodule.entity.QuestionBean
import com.example.basemodule.network.Api
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * create by libo
 * create on 2021/6/29
 * description
 */
interface QaService {

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<QaService>()
    }

    @GET(Api.QUESTION)
    suspend fun getQaData(@Path("page") page: Int): BaseData<QuestionBean>
}