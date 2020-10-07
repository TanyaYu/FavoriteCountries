package com.tanyaiuferova.favoritecountries.network

import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
sealed class HttpRequest(
    private val url: HttpUrl,
    private val method: String
) : RequestFactory {

    internal val internalQueries = mutableMapOf<String, String?>()
    internal val internalHeaders = mutableMapOf<String, String>()
    internal var body: RequestBody? = null

    override fun create(): Request = Request.Builder()
        .method(method, body)
        .url(
            url.newBuilder()
                .apply {
                    internalQueries.forEach { query ->
                        addQueryParameter(query.key, query.value)
                    }
                }
                .build()
        )
        .headers(Headers.of(internalHeaders))
        .build()
}

class GetRequest(url: HttpUrl) : HttpRequest(url, "GET")

//TODO: Implement other request methods

class ParamsBuilder(
    private val request: HttpRequest
) {
    infix fun String.to(value: Any?) = request.internalQueries.set(this, value?.toString())
}

inline fun HttpRequest.params(body: ParamsBuilder.() -> Unit) = ParamsBuilder(this).apply(body)