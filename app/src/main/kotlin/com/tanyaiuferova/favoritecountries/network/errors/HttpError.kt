package com.tanyaiuferova.favoritecountries.network.errors

import okhttp3.Response

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class HttpError(private val response: Response) : RuntimeException(response.toString())