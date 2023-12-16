package com.example.flowmvihilt.application

import android.content.Context
import com.example.basemodule.util.LanguageHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * 多语言初始化
 */
class LanguageInitTask @Inject constructor(@ApplicationContext val context: Context): Runnable {
    override fun run() {
        LanguageHelper.getAttachBaseContext(context)
    }
}