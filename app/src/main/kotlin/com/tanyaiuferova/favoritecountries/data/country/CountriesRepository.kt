package com.tanyaiuferova.favoritecountries.data.country

import com.tanyaiuferova.favoritecountries.data.network.WorldBankAPI
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountriesRepository @Inject constructor(
    private val api: WorldBankAPI,
    private val countriesDao: CountriesDao
) {

    fun getAllCountries(page: Int): Single<List<CountryResponse>> {
        return api.getCountries()
            .subscribeOn(Schedulers.io)
    }

    fun insert(countries: List<Country>): Completable {
        return countriesDao.insertAll(countries)
            .subscribeOn(Schedulers.computation)
    }

    fun getLocal() = countriesDao.getAll()
        .subscribeOn(Schedulers.computation)
}