package com.tanyaiuferova.favoritecountries.network.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.PageInfo
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountriesResponse
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import java.lang.reflect.Type

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountriesResponseDeserializer : JsonDeserializer<CountriesResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CountriesResponse {

        val firstObject = json.asJsonArray[0]
        val secondObject = json.asJsonArray[1]

        val pageInfo = context.deserialize<PageInfo>(firstObject, PageInfo::class.java)
        val countries = context.deserialize<List<CountryResponse>>(
            secondObject,
            object : TypeToken<List<CountryResponse>>() {}.type
        )

        return CountriesResponse(
            pageInfo,
            countries
        )
    }
}