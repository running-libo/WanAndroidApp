package com.example.flowmvihilt.ui.main

import com.example.basemodule.basemvi.IUiIntent

abstract class MainIntent: IUiIntent {
    data class getDetail(val page: Int): MainIntent()

    object getBanner: MainIntent()
}