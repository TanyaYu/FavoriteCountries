package com.tanyaiuferova.favoritecountries.di.network

import com.tanyaiuferova.favoritecountries.network.ErrorInterceptor
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

    override fun get(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(ErrorInterceptor())
        .build()
}