package com.tanyaiuferova.favoritecountries.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
object Schedulers {
    val main = AndroidSchedulers.mainThread()
    val computation = Schedulers.computation()
    val io = Schedulers.io()
}
