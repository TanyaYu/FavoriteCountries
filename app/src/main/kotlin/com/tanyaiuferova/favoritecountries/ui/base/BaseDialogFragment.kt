package com.tanyaiuferova.favoritecountries.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanyaiuferova.favoritecountries.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
open class BaseDialogFragment(@LayoutRes val contentLayoutId: Int) : AppCompatDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val disposable = CompositeDisposable()

    inline fun <reified VM : ViewModel> viewModels() = viewModels<VM>(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        onAttached()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = Dialog(requireContext(), R.style.ModalTheme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentLayoutId, container, false)
    }

    open fun onAttached() {

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}