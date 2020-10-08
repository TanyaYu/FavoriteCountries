package com.tanyaiuferova.favoritecountries.viewmodels

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import com.tanyaiuferova.favoritecountries.data.country.Country
import com.tanyaiuferova.favoritecountries.data.country.toSearchItem
import com.tanyaiuferova.favoritecountries.pagination.Pagination
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchItem
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.utils.mapList
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel.State.*
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

    private val stateSubject = BehaviorSubject.createDefault(LOADING)
    private val countriesSubject = BehaviorSubject.create<List<CountrySearchItem>>()
    private val searchQuery = BehaviorSubject.createDefault<String>("")

    private val pagination = Pagination(
        dataProvider = ::requestPage,
        viewInteraction = this
    )

    private var isFirstPageLoaded = false

    init {
        pagination.start()

        disposable += Observables.combineLatest(
            searchQuery.debounce(300, TimeUnit.MILLISECONDS),
            countriesRepository.getAllFromCache().toObservable()
        ) { query, _ -> query }
            .flatMapMaybe(countriesRepository::search)
            .skipWhile { it.isEmpty() && !isFirstPageLoaded } // process the first page request before submitting an empty list
            .mapList(Country::toSearchItem)
            .observeOn(Schedulers.main)
            .subscribeBy(
                onNext = { data ->
                    if (data.isEmpty()) {
                        stateSubject.onNext(EMPTY)
                    } else {
                        countriesSubject.onNext(data)
                        stateSubject.onNext(DATA)
                    }
                },
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
        isFirstPageLoaded = true
        stateSubject.onNext(DATA)
    }

    override fun onError(error: Throwable) {
        stateSubject.onNext(ERROR)
        // TODO check for different types of errors
    }

    override fun onEmpty() {
        stateSubject.onNext(EMPTY)
    }

    override fun onLoading() {
        stateSubject.onNext(LOADING)
    }

    override fun onNextPage(data: List<Country>) {
        stateSubject.onNext(DATA)
    }

    override fun onPageError(error: Throwable) {
        stateSubject.onNext(PAGE_ERROR)
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
        PAGE_ERROR,
        EMPTY
    }

    companion object {
        const val PAGE_SIZE = 50
    }
}