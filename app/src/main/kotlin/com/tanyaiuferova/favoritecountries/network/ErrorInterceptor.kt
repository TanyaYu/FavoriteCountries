package com.tanyaiuferova.favoritecountries.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class ErrorInterceptor(
//    private val networkStatusReceiver: NetworkStatusReceiver
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!networkStatusReceiver.isNetworkAvailable()) {
//            throw NoNetworkException
//        }

        return chain.proceed(chain.request())
    }
}