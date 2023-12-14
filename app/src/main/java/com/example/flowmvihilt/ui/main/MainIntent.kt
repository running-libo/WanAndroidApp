package com.example.flowmvihilt.ui.main

import com.example.basemodule.base.IUiIntent

abstract class MainIntent: IUiIntent {
    data class getDetail(val page: Int): MainIntent()
}