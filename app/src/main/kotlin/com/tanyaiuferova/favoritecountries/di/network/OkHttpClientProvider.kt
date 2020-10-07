package com.tanyaiuferova.favoritecountries.di.network

import okhttp3.OkHttpClient
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class OkHttpClientProvider() : Provider<OkHttpClient> {

    override fun get(): OkHttpClient = OkHttpClient.Builder()
        .build()
}