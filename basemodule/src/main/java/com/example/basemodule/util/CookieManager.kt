package com.example.basemodule.util

import android.content.Context
import android.os.Build
import android.webkit.CookieManager
import android.webkit.WebIconDatabase
import android.webkit.WebStorage
import android.webkit.WebViewDatabase
import androidx.annotation.RequiresApi


/**
 * 对webview的数据的各种操作
 */
class CookieManager {

    /**
     * 设置是否保存cookie
     */
    fun setAcceptCookies(accept: Boolean) {
        CookieManager.getInstance().setAcceptCookie(accept)
    }

    /**
     * 清理cookie
     */
    fun clearCookie() {
        CookieManager.getInstance().removeAllCookie()
    }

    /**
     * 获取某个网站cookie
     */
    fun getCookie(url: String) {
        CookieManager.getInstance().getCookie(url)
    }

    /**
     * 同步cookie
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun synchCookie() {
        CookieManager.getInstance().flush()
    }

    /**
     * 清理webview网站账号密码
     */
    @RequiresApi(Build.VERSION_CODES.ECLAIR_MR1)
    fun clearPasswords(context: Context) {
        var db = WebViewDatabase.getInstance(context)
        db.clearUsernamePassword()
        db.clearHttpAuthUsernamePassword()
    }

    @RequiresApi(Build.VERSION_CODES.ECLAIR_MR1)
    fun clearDataBase() {
        WebStorage.getInstance().deleteAllData()
    }

    fun clearCache() {
        WebIconDatabase.getInstance().removeAllIcons()
    }

    /**
     * 清理缓存
     */
    fun clearAllData(context: Context) {
        clearCookie()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            clearPasswords(context)
            clearDataBase()
        }
        clearCache()
    }
}