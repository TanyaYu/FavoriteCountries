package com.tanyaiuferova.favoritecountries.viewmodels

import androidx.lifecycle.ViewModel
import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel() {

    init {
        disposable += countriesRepository.insert(listOf(Country("RU", "Russia")))
            .observeOn(Schedulers.main)
            .subscribeBy(Timber::e) { Timber.d("inserted")}
    }
    val local = countriesRepository.getLocal()
    val countries = countriesRepository.getAllCountries(1)
}