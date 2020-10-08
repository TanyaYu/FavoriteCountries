package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.data.country.toFavoriteItem
import com.tanyaiuferova.favoritecountries.utils.mapList
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel() {

    val favorites = countriesRepository.getFavorites()
        .mapList(Country::toFavoriteItem)

    val state = favorites.map { list ->
        if (list.isEmpty()) State.EMPTY
        else State.DATA
    }

    init {
        disposable += countriesRepository.clearCache().subscribe()
    }

    enum class State {
        DATA,
        EMPTY
    }
}