package com.example.basemodule.util

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

object LanguageHelper {

    fun getAttachBaseContext(context: Context): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return setAppLanguageApi24(context)
        } else {
            setAppLanguage(context)
        }
        return context
    }

    /**
     * 获取当前系统语言，如未包含则默认英文
     */
    private fun getSystemLocale(): Locale {
        val systemLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault()[0]
        } else {
            Locale.getDefault()
        }
        return when (systemLocale.language) {
            Locale.CHINA.language -> {
                Locale.CHINA
            }
            Locale.ENGLISH.language -> {
                Locale.ENGLISH
            }
            else -> {
                Locale.ENGLISH
            }
        }
    }

    /**
     * 兼容 7.0 及以上
     */
    @TargetApi(Build.VERSION_CODES.N)
    private fun setAppLanguageApi24(context: Context): Context {
        val locale = getSystemLocale()
        val resource = context.resources
        val configuration = resource.configuration
        configuration.setLocale(locale)
        configuration.setLocales(LocaleList(locale))
        return context.createConfigurationContext(configuration)
    }

    /**
     * 设置应用语言
     */
    private fun setAppLanguage(context: Context) {
        val resources = context.resources
        val displayMetrics = resources.displayMetrics
        val configuration = resources.configuration
        // 获取当前系统语言，默认设置跟随系统
        val locale = getSystemLocale()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, displayMetrics)
    }

    /**
     * 获取当前系统语言显示
     */
    fun getSystemLanguage(): String? = getSystemLocale().language
}