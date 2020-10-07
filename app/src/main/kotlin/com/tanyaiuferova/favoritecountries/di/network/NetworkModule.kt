package com.tanyaiuferova.favoritecountries.di.network

import com.google.gson.Gson
import com.tanyaiuferova.favoritecountries.di.WORLD_BANK_URL_QUALIFIER
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
@Module
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient = OkHttpClientProvider().get()

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson = GsonProvider().get()

    @Provides
    @Named(WORLD_BANK_URL_QUALIFIER)
    @JvmStatic
    fun provideWorldBankBaseUrl(): HttpUrl = BaseUrlProvider().get()
}