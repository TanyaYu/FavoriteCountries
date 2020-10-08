package com.tanyaiuferova.favoritecountries.data.country

import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchItem

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
fun CountryResponse.toEntity() = Country(
    id = id.orEmpty(),
    name = name.orEmpty()
)

fun Country.toCountrySearchItem() = CountrySearchItem(
    id = id,
    title = "[$id] $name"
)