package com.tanyaiuferova.favoritecountries.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
open class RxViewModel : ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}