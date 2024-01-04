package com.example.flowmvihilt.gongzhonghao

import com.example.basemodule.basemvi.IUiIntent

abstract class WxArticalIntent: IUiIntent {
    data class gettitles(val page: Int): WxArticalIntent()

    data class getDataList(val id: Int, val page: Int): WxArticalIntent()
}