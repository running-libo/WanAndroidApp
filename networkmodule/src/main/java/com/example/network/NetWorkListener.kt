package com.example.network

import android.util.Log
import com.example.basemodule.constants.Tags.Companion.HTTP
import okhttp3.*
import okhttp3.EventListener.Factory
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * @author: libo
 * @date: 2023/4/26 09:38
 * @Description: 监听okhttp请求，包括连接总时长和请求总时长
 */
open class NetWorkListener: EventListener() {
    private var dnsStart: Long = 0 //dns开始解析时间
    private var connectStart: Long = 0  //开始连接时间
    private var callStart: Long = 0  //开始请求时间
    private var connectAquire: Long = 0  //连接获取时间

    companion object {
        fun get(): Factory {
            return Factory { NetWorkListener() }
        }
    }

    override fun callStart(call: Call) {
        Log.v(HTTP, "callStart")

        callStart = System.nanoTime()
    }

    override fun dnsStart(call: Call, domainName: String) {
        Log.v(HTTP, "dnsStart")

        dnsStart = System.nanoTime()
    }

    override fun dnsEnd(call: Call, domainName: String, inetAddressList: MutableList<InetAddress>) {
        Log.v(HTTP, "dnsEnd")

        var dnsEnd = System.nanoTime()
        var duration = TimeUnit.NANOSECONDS.toMillis(dnsEnd - dnsStart)
        Log.v(HTTP, "dns_time_l(域名解析时长): $duration ms")
    }

    override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {
        Log.v(HTTP, "connectStart")

        connectStart = System.nanoTime()
    }

    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        Log.v(HTTP, "connectEnd")

        connectAquire = System.nanoTime()
        var duration = TimeUnit.NANOSECONDS.toMillis(connectAquire - connectStart)
        Log.v(HTTP, "connecting_time_l(建连时长): $duration ms")
    }

    override fun connectionAcquired(call: Call, connection: Connection) {
        Log.v(HTTP, "connectionAcquired")
    }

    override fun connectionReleased(call: Call, connection: Connection) {
        Log.v(HTTP, "connectionReleased")

        var connectReleased = System.nanoTime()
        var duration = TimeUnit.NANOSECONDS.toMillis(connectReleased - connectAquire)
        Log.v(HTTP, "connected_time_l(连接保持时长): $duration ms")
    }

    override fun callEnd(call: Call) {
        Log.v(HTTP, "callEnd")

        var callEnd = System.nanoTime()
        var duration = TimeUnit.NANOSECONDS.toMillis(callEnd - callStart)
        Log.v(HTTP, "total_time_l(请求总时长): $duration ms")
    }

    override fun callFailed(call: Call, ioe: IOException) {
        Log.v(HTTP, "callFailed")

        var callEnd = System.nanoTime()
        var duration = TimeUnit.NANOSECONDS.toMillis(callEnd - callStart)
        Log.v(HTTP, "total_time_l(请求总时长): $duration ms")
    }
}