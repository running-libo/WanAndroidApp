package com.example.flowmvihilt.system

import com.example.basemodule.basemvi.IUiIntent

abstract class SysIntent: IUiIntent {
    data class getDatas(val page: Int): SysIntent()
}