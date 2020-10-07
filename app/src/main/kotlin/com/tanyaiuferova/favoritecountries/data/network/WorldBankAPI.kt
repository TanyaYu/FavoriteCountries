package com.tanyaiuferova.favoritecountries.data.network

import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountriesResponse
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import com.tanyaiuferova.favoritecountries.network.Http
import com.tanyaiuferova.favoritecountries.network.get
import com.tanyaiuferova.favoritecountries.network.params
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class WorldBankAPI @Inject constructor(
    private val http: Http
) {
    fun getCountries(
        page: Int? = null,
        perPage: Int? = null,
        format: String = "json"
    ): Single<List<CountryResponse>> {
        return http.get<CountriesResponse>("countries") {
            params {
                "format" to format
                if (page != null) "page" to page.toString()
                if (perPage != null) "per_page" to perPage.toString()
            }
        }.map { it.countries }
    }
}
