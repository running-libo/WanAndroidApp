package com.example.basemodule.constants

/**
 * 日志tag类
 */
interface Tags {

    companion object {

        private const val WAN_ANDROID = "wanandroid"

        /**
         * application生命周期变化日志
         */
        const val APPLICATION = WAN_ANDROID + "application"

        /**
         * activity生命周期变化日志
         */
        const val ACTIVITY = WAN_ANDROID + "activity"

        /**
         * Webview日志
         */
        const val WEBVIEW = WAN_ANDROID + "webview"
    }
}