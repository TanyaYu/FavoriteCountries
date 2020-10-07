package com.tanyaiuferova.favoritecountries.ui

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val disposable = CompositeDisposable()

    inline fun <reified VM : ViewModel> viewModels() = viewModels<VM>(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        onAttached()
    }

    open fun onAttached() {

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}