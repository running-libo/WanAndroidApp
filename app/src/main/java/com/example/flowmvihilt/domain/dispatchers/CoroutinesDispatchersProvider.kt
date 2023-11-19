package com.example.flowmvihilt.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatchersProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}