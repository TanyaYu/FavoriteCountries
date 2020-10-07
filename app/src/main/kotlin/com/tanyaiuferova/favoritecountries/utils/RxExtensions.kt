package com.tanyaiuferova.favoritecountries.utils

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
fun <X: Any, Y: Any> Observable<List<X>>.mapList(transformer: (X) -> Y) : Observable<List<Y>> {
    return this.map { it.map(transformer) }
}

fun <X: Any, Y: Any> Single<List<X>>.mapList(transformer: (X) -> Y) : Single<List<Y>> {
    return this.map { it.map(transformer) }
}

fun <X: Any, Y: Any> Flowable<List<X>>.mapList(transformer: (X) -> Y) : Flowable<List<Y>> {
    return this.map { it.map(transformer) }
}

fun <X, Y, Z> Observable<X>.combineLatest(observable: Observable<Y>, combiner: (X, Y) -> Z): Observable<Z> {
    return Observable.combineLatest(this, observable, BiFunction(combiner))
}