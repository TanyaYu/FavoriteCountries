package com.tanyaiuferova.favoritecountries.utils

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

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

fun <X: Any, Y: Any> Maybe<List<X>>.mapList(transformer: (X) -> Y) : Maybe<List<Y>> {
    return this.map { it.map(transformer) }
}