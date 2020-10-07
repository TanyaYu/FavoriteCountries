package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel() {
    init {
        disposable += countriesRepository.clearCache().subscribe()
    }
}