package com.tanyaiuferova.favoritecountries.pagination

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy

/**
 * Author: Tanya Yuferova
 * Date: 10/7/2020
 */
class Pagination<T>(
    val dataProvider: (page: Int) -> Single<List<T>>,
    val viewInteraction: ViewInteraction<T>
) {
    private var page = 1

    private var state: State = Initial()

    private val disposable = CompositeDisposable()

    private fun transitTo(state: State) {
        this.state = state
        state.onEnter()
    }

    fun start() {
        disposable.clear()
        transitTo(Loading())
    }

    fun retry() = state.onRetry()

    fun retryPage() = state.onPageRetry()

    fun loadNewPage() = state.onPageRequest()

    fun clear() {
        disposable.dispose()
    }

    abstract class State {
        open fun onEnter() {}
        open fun onRetry() {}
        open fun onPageRetry() {}
        open fun onPageRequest() {}
    }

    class Initial : State()

    inner class Empty : State() {
        override fun onEnter() {
            viewInteraction.onEmpty()
        }
    }

    inner class Loading : State() {
        override fun onEnter() {
            viewInteraction.onLoading()

            page = 1
            disposable += dataProvider.invoke(page)
                .subscribeBy(
                    onSuccess = { newData ->
                        if (newData.isEmpty()) {
                            transitTo(Empty())
                        } else {
                            page++
                            transitTo(Data(newData))
                        }
                    },
                    onError = {
                        transitTo(Error(it))
                    }
                )
        }
    }

    inner class Data(val data: List<T>) : State() {
        override fun onEnter() {
            if (page > 2)
                viewInteraction.onNextPage(data)
            else viewInteraction.onFirstPage(data)
        }

        override fun onPageRequest() {
            transitTo(PageLoading())
        }
    }

    inner class Error(val error: Throwable) : State() {
        override fun onEnter() {
            viewInteraction.onError(error)
        }

        override fun onRetry() {
            transitTo(Loading())
        }
    }

    inner class PageLoading : State() {
        override fun onEnter() {
            viewInteraction.onPageLoading()

            disposable += dataProvider.invoke(page)
                .subscribeBy(
                    onSuccess = { newData ->
                        if (newData.isEmpty()) {
                            transitTo(Done())
                        } else {
                            page++
                            transitTo(Data(newData))
                        }
                    },
                    onError = {
                        transitTo(PageError(it))
                    }
                )
        }
    }

    inner class PageError(private val error: Throwable) : State() {
        override fun onEnter() {
            viewInteraction.onPageError(error)
        }

        override fun onPageRetry() {
            transitTo(PageLoading())
        }
    }

    inner class Done : State() {
        override fun onEnter() {
            viewInteraction.onComplete()
        }
    }

    interface ViewInteraction<T> {
        fun onLoading()
        fun onFirstPage(data: List<T>)
        fun onError(error: Throwable)
        fun onEmpty()

        fun onNextPage(data: List<T>)
        fun onPageError(error: Throwable)
        fun onPageLoading()

        fun onComplete()
    }
}