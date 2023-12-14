package com.example.basemodule.network

interface Api {

    companion object {
        const val SERVER_ADDRESS_RELEASE = "https://www.wanandroid.com/"

        /**
         * 首页
         */
        const val HOME_PAGE = "article/list/{page}/json"
    }

}