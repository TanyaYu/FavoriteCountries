package com.tanyaiuferova.favoritecountries.data.country

import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import com.tanyaiuferova.favoritecountries.ui.countrydetails.CountryDetailsItem
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchItem
import com.tanyaiuferova.favoritecountries.ui.home.FavoriteCountryItem

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
fun CountryResponse.toEntity() = Country(
    id = id.orEmpty(),
    name = name.orEmpty()
)

fun Country.toSearchItem() = CountrySearchItem(
    id = id,
    title = "[$id] $name"
)

fun Country.toFavoriteItem() = FavoriteCountryItem(
    id = id,
    title = name,
    notes = notes.orEmpty()
)

fun Country.toDetailsItem() = CountryDetailsItem(
    id = id,
    title = name,
    notes = notes.orEmpty()
)