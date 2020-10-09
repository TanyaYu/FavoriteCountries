package com.tanyaiuferova.favoritecountries.di.network

import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class OkHttpClientProvider() : Provider<OkHttpClient> {

    // Add legacy cipher suite for Android 4
    // https://github.com/square/okhttp/issues/4053
    private val cipherSuites = mutableListOf<CipherSuite>().apply {
        addAll(ConnectionSpec.MODERN_TLS.cipherSuites()!!)
        add(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA)
        add(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA)
    }

    private val legacyTls = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .cipherSuites(*cipherSuites.toTypedArray())
        .build()

    override fun get(): OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(legacyTls, ConnectionSpec.CLEARTEXT))
        .build()
}