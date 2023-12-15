package com.example.basemodule.util

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View

object GrayManager {
    private var paint: Paint?= null
    private var colorMatrix: ColorMatrix?= null

    init {
        paint = Paint()
        colorMatrix = ColorMatrix().apply {
            setSaturation(0f)  //设置灰度效果
        }
        paint?.colorFilter = ColorMatrixColorFilter(colorMatrix!!)
    }

    fun setColorThemeMode(view: View) {
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
    }
}