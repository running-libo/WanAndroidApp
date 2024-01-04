package com.example.flowmvihilt.gongzhonghao

import com.example.basemodule.basemvi.IUiIntent

abstract class WxArticalIntent: IUiIntent {
    data class getDatas(val page: Int): WxArticalIntent()
}