package com.tanyaiuferova.favoritecountries.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.viewmodels.HomeViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
    }

    override fun onAttached() {
        disposable += viewModel.countries
            .observeOn(Schedulers.main)
            .subscribeBy(Timber::e) {Timber.d(it.toString())}
        disposable += viewModel.local
            .observeOn(Schedulers.main)
            .subscribeBy(Timber::e) {Timber.d(it.toString())}
    }
}