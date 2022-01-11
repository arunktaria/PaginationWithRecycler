package com.example.paginationwithrecycler

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@HiltAndroidApp
class BaseModule : Application() {
    @Singleton
    @Provides
    fun getRetroInstance(): RetroApi {
        return Retrofit.Builder()
            .baseUrl("https://api.instantwebtools.net/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetroApi::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(retroApi: RetroApi): RetroHelper   {
        return RepositoryImpl(retroApi)
    }


}