package com.tanyaiuferova.favoritecountries.di.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountriesResponse
import com.tanyaiuferova.favoritecountries.network.deserializers.CountriesResponseDeserializer
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class GsonProvider : Provider<Gson> {

    override fun get(): Gson = GsonBuilder().setLenient()
        .registerTypeAdapter(CountriesResponse::class.java, CountriesResponseDeserializer())
        .create()
}