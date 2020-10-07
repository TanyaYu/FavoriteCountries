package com.tanyaiuferova.favoritecountries.data.country

import com.tanyaiuferova.favoritecountries.data.network.WorldBankAPI
import com.tanyaiuferova.favoritecountries.data.network.responsemodels.country.CountryResponse
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.utils.mapList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
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

    fun loadInCache(page: Int, perPage: Int): Completable {
        return getAllFromNetwork(page, perPage)
            .mapList(CountryResponse::toEntity)
            .flatMapCompletable(::updateCache)
    }

    fun getLoadedInCache(page: Int, perPage: Int): Single<List<Country>> {
        return getAllFromNetwork(page, perPage)
            .mapList(CountryResponse::toEntity)
            .flatMap { list ->
                updateCache(list)
                    .toSingleDefault(list)
            }
    }


    fun getAllFromNetwork(page: Int, perPage: Int): Single<List<CountryResponse>> {
        return api.getCountries(page, perPage)
            .subscribeOn(Schedulers.io)
            .map { list ->
                list.filter { country ->
                    !country.capitalCity.isNullOrEmpty()
                }
            }
    }

    fun updateCache(countries: List<Country>): Completable {
        return countriesDao.insertAll(countries)
            .subscribeOn(Schedulers.computation)
    }

    fun getAllFromCache(): Flowable<List<Country>> {
        return countriesDao.getAll()
            .subscribeOn(Schedulers.computation)
    }

    fun getFavorites(): Flowable<List<Country>> {
        return countriesDao.getFavorites(true)
            .subscribeOn(Schedulers.computation)
    }

    fun getNonFavorites(): Flowable<List<Country>> {
        return countriesDao.getFavorites(false)
            .subscribeOn(Schedulers.computation)
    }

    fun addToFavorites(countryId: String): Completable {
        return countriesDao.updateFavorite(countryId, true)
            .subscribeOn(Schedulers.computation)
    }

    fun search(pattern: String): Flowable<List<Country>> {
        return countriesDao.search(pattern)
            .subscribeOn(Schedulers.computation)
    }

    fun deleteFromFavorites(countryId: String): Completable {
        return countriesDao.updateFavorite(countryId, false)
            .subscribeOn(Schedulers.computation)
    }

    fun clearCache(): Completable {
        return countriesDao.deleteAllUnfavorites()
            .subscribeOn(Schedulers.computation)
    }
}