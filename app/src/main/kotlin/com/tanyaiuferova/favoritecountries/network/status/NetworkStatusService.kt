package com.tanyaiuferova.favoritecountries.network.status

import io.reactivex.rxjava3.core.Observable

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
interface NetworkStatusService {
    fun observe(): Observable<Boolean>
    fun isNetworkAvailable(): Boolean
}