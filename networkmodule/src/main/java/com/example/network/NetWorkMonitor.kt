package com.example.network

import okhttp3.OkHttpClient

/**
 * @author: libo
 * @date: 2023/4/28 15:02
 * @Description: 网络请求监控
 */

fun OkHttpClient.Builder.addNetWorkMonitor(): OkHttpClient.Builder {
    this.eventListenerFactory(NetWorkListener.get())
        .addNetworkInterceptor(NetworkInterceptor())
        .addInterceptor(NetCacheInterceptor())
    return this
}