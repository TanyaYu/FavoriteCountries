package com.tanyaiuferova.favoritecountries.di.network

import okhttp3.HttpUrl
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class BaseUrlProvider : Provider<HttpUrl> {
    override fun get(): HttpUrl = HttpUrl.Builder()
        .scheme("https")
        .host("api.worldbank.org")
        .addPathSegment("v2")
        .build()
}