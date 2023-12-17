package com.example.network

import android.util.Log
import com.example.basemodule.constants.Tags.Companion.HTTP
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * create by libo
 * create on 2023/4/25
 * description Okhttp请求和返回数据日志拦截器
 */
class NetworkInterceptor : Interceptor {
    private val byteCount = 1024 * 1024

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //chain里面包含了request和response，按需获取

        val request = chain.request()
        val response = chain.proceed(request)

        Log.v(HTTP, "url_s(请求地址): ${request.url()}")
        Log.v(HTTP, "请求方式: ${request.method()}")

        if (request.body() == null) {
            Log.v(HTTP, "req_body_size_l(请求体大小): 0")
        } else {
            val requestBody = request.body()
            Log.v(HTTP, "req_body_size_l(请求体大小): ${requestBody?.contentLength()}")
        }

        val responseBody = response.peekBody(byteCount.toLong())

        Log.v(HTTP, "res_body_size_l(响应体大小): ${responseBody.contentLength()}")
        Log.v(HTTP, "响应内容: ${responseBody.string()}")
        return response
    }
}