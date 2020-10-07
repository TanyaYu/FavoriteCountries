package com.tanyaiuferova.favoritecountries.data.network.responsemodels.country

import com.tanyaiuferova.favoritecountries.data.network.responsemodels.PageInfo

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
data class CountriesResponse(
    val pageInfo: PageInfo,
    val countries: List<CountryResponse>
)