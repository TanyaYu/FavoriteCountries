package com.tanyaiuferova.favoritecountries.data.country

import com.tanyaiuferova.favoritecountries.data.network.WorldBankAPI
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountriesRepository @Inject constructor(
    private val api: WorldBankAPI
) {

    fun getAllCountries(page: Int): Single<List<CountryResponse>> {
        return api.getCountries()
    }
}