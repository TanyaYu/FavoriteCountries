package com.tanyaiuferova.favoritecountries.network

import okhttp3.Request

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
interface RequestFactory {
    fun create(): Request
}