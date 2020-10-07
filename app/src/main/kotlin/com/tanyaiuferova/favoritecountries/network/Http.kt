package com.tanyaiuferova.favoritecountries.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tanyaiuferova.favoritecountries.di.WORLD_BANK_URL_QUALIFIER
import com.tanyaiuferova.favoritecountries.network.errors.HttpError
import io.reactivex.rxjava3.core.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Named

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class Http @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    @Named(WORLD_BANK_URL_QUALIFIER)
    private val baseUrl: HttpUrl
) {
    fun <T> get(
        query: String,
        type: Type,
        requestBuilder: GetRequest.() -> Unit = {}
    ): Single<T> {
        return Single.create { emitter ->
            val url = baseUrl.newBuilder()
                .addPathSegment(query)
                .build()

            val request = GetRequest(url)
                .also(requestBuilder)
                .create()

            val call = okHttpClient.newCall(request)

            emitter.setCancellable { call.cancel() }

            val response = call.execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val parsedBody = gson.fromJson<T>(body.charStream(), type)
                emitter.onSuccess(parsedBody)
            } else {
                emitter.onError(HttpError(response))
            }
        }
    }

    //TODO: Implement other methods
}

// To enable type T
inline fun <reified T> Http.get(
    path: String = "",
    noinline requestBuilder: GetRequest.() -> Unit = {}
): Single<T> = get(path, object : TypeToken<T>() {}.type, requestBuilder)


