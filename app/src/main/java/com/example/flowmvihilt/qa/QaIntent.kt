package com.example.flowmvihilt.qa

import com.example.basemodule.basemvi.IUiIntent

abstract class QaIntent: IUiIntent {
    data class getDatas(val page: Int): QaIntent()
}