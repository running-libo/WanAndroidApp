package com.example.flowmvihilt.qa

import com.example.basemodule.base.IUiIntent

abstract class QaIntent: IUiIntent {
    data class getDatas(val page: Int): QaIntent()
}