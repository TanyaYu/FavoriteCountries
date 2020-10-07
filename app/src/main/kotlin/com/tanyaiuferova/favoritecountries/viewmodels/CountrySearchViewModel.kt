package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.data.country.toCountrySearchItem
import com.tanyaiuferova.favoritecountries.pagination.Pagination
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.utils.mapList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountrySearchViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel(), Pagination.ViewInteraction<Country> {

    private val stateSubject = BehaviorSubject.createDefault(State.LOADING)

    val countries = countriesRepository.getNonFavorites()
        .mapList(Country::toCountrySearchItem)

    val state = stateSubject.hide()

    private val pagination =
        Pagination(
            dataProvider = ::requestPage,
            viewInteraction = this
        )

    init {
        pagination.start()
    }

    override fun onNewData(data: List<Country>) {
        stateSubject.onNext(State.DATA)
    }

    override fun onError(error: Throwable) {
        stateSubject.onNext(State.ERROR)
    }

    override fun onEmpty() {
        stateSubject.onNext(State.EMPTY)
    }

    override fun onLoading() {
        stateSubject.onNext(State.LOADING)
    }

    override fun onNewPage(data: List<Country>) {
        stateSubject.onNext(State.DATA)
    }

    override fun onPageError(error: Throwable) {
        Timber.e(error)
        //TODO: Inform user about error
    }

    override fun onPageLoading() {
        Timber.d("Page loading")
        //TODO: Inform user about loading
    }

    override fun onComplete() {
        Timber.d("Loading complete")
    }

    fun onRetryClick() {
        pagination.retry()
    }

    fun onNewPageRequest() {
        pagination.loadNewPage()
    }

    private fun requestPage(page: Int): Single<List<Country>> {
        return countriesRepository.getLoadedInCache(page, PAGE_SIZE)
            .doOnError(Timber::e)
            .observeOn(Schedulers.main)
    }

    override fun onCleared() {
        super.onCleared()
        pagination.clear()
    }

    enum class State {
        LOADING,
        DATA,
        ERROR,
        EMPTY
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}