package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.data.country.toDetailsItem
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class CountryDetailsViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel() {

    private val idSubject = BehaviorSubject.create<String>()

    val country = idSubject
        .flatMapMaybe(countriesRepository::getById)
        .map(Country::toDetailsItem)

    fun onIdChanged(id: String) {
        idSubject.onNext(id)
    }

    fun saveNotes(notes: String) {
        disposable += idSubject
            .flatMapCompletable { id ->
                countriesRepository.addToFavorites(id, notes)
            }.subscribeBy()
    }
}