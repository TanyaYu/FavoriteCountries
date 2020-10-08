package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.data.country.toCountrySearchItem
import com.tanyaiuferova.favoritecountries.pagination.Pagination
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchItem
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.utils.mapList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountrySearchViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : RxViewModel(), Pagination.ViewInteraction<Country> {

    val countries get() = countriesSubject.hide()
    val state get() = stateSubject.hide()

    private val stateSubject = BehaviorSubject.createDefault(State.LOADING)
    private val countriesSubject = BehaviorSubject.create<List<CountrySearchItem>>()
    private val searchQuery = BehaviorSubject.createDefault<String>("")

    private val pagination = Pagination(
        dataProvider = ::requestPage,
        viewInteraction = this
    )

    init {
        pagination.start()

        disposable += Observables.combineLatest(
            searchQuery.debounce(500, TimeUnit.MILLISECONDS),
            countriesRepository.getAllFromCache().toObservable()
        ) { query, _ -> query }
            .flatMapMaybe(countriesRepository::search)
            .mapList(Country::toCountrySearchItem)
            .observeOn(Schedulers.main)
            .subscribeBy(
                onNext = countriesSubject::onNext,
                onError = ::onError
            )
    }


    fun onNewPageRequest() {
        pagination.loadNewPage()
    }

    fun onRetryClick() {
        pagination.retry()
    }

    fun onQueryChanged(query: String) {
        searchQuery.onNext(query)
    }

    private fun requestPage(page: Int): Single<List<Country>> {
        return countriesRepository.getLoadedInCache(page, PAGE_SIZE)
            .doOnError(Timber::e)
            .observeOn(Schedulers.main)
    }

    override fun onFirstPage(data: List<Country>) {
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

    override fun onNextPage(data: List<Country>) {
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