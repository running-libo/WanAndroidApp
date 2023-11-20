package com.example.flowmvihilt.ui.main

import com.example.flowmvihilt.base.IUiIntent

abstract class MainIntent: IUiIntent {
    data class getDetail(val page: Int): MainIntent()
}