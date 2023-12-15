package com.example.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * create by libo
 * create on 2021/6/29
 * description 发送请求、返回数据日志拦截器
 */
class LogInterceptor @Inject constructor() : Interceptor {
    private val byteCount = 1024 * 1024 * 10

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.i("loger", String.format("发送请求  %s", request.url))

        val responseBody = response.peekBody(byteCount.toLong())
        Log.i("loger", String.format("接收响应  %s", responseBody.string()))

        return response
    }

}