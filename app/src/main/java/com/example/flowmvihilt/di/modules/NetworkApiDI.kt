package com.example.flowmvihilt.di.modules

import com.example.flowmvihilt.data.remote.MainApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkApiDI {

    @Provides
    fun mainApi(retrofit: Retrofit): MainApiService {
        return MainApiService(retrofit)
    }
}