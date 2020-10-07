package com.tanyaiuferova.favoritecountries.network.status

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class NetworkStatusReceiver(
    private val context: Context
) : BroadcastReceiver(), NetworkStatusService {

    private val networkInfoSubject = BehaviorSubject.create<Boolean>()

    override fun onReceive(context: Context, intent: Intent?) {
        networkInfoSubject.onNext(context.isConnected())
    }

    override fun observe(): Observable<Boolean> {
        return networkInfoSubject.hide()
    }

    override fun isNetworkAvailable(): Boolean {
        return networkInfoSubject.value ?: context.isConnected()
    }

    private fun Context.isConnected() =
        (applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
}