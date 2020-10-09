package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class CountryDetailsViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel() {

    val notes get() = notesSubject.hide().distinct()
    val title get() = titleSubject.hide().firstOrError()

    private val idSubject = BehaviorSubject.create<String>()
    private val notesSubject = BehaviorSubject.create<String>()
    private val titleSubject = BehaviorSubject.create<String>()

    init {
        disposable += idSubject
            .flatMapMaybe(countriesRepository::getById)
            .firstElement()
            .subscribeBy(onSuccess = { item ->
                notesSubject.onNext(item.notes.orEmpty())
                titleSubject.onNext(item.name)
            }, onError = Timber::e)
    }

    fun updateId(id: String) {
        idSubject.onNext(id)
    }

    fun updateNotes(notes: String) {
        notesSubject.onNext(notes)
    }

    fun save() {
        val id = idSubject.value
        val note = notesSubject.value
        disposable += countriesRepository.addToFavorites(id, note).subscribeBy()
    }
}