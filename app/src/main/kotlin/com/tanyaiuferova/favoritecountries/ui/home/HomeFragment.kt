package com.tanyaiuferova.favoritecountries.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.ui.views.ViewSwitcher
import com.tanyaiuferova.favoritecountries.utils.Schedulers.main
import com.tanyaiuferova.favoritecountries.viewmodels.HomeViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.properties.Delegates

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter = FavoriteCountriesAdapter(
        onItemClick = ::onCountryClick
    )

    private val viewSwitcher by lazy {
        ViewSwitcher(recycler, empty_view)
    }

    //TODO implement DataBinding
    private var state by Delegates.observable(HomeViewModel.State.DATA) { _, _, newValue ->
        if (view != null) bindState(newValue)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(recycler) {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        add_btn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
        bindState(state)
    }

    override fun onAttached() {
        disposable += viewModel.favorites
            .observeOn(main)
            .subscribeBy(onNext = ::bindFavorites)

        disposable += viewModel.state
            .observeOn(main)
            .subscribeBy(onNext = { state = it})
    }

    private fun bindFavorites(list: List<FavoriteCountryItem>) {
        adapter.submitList(list)
    }

    private fun bindState(state: HomeViewModel.State) {
        when (state) {
            HomeViewModel.State.DATA -> viewSwitcher.display(recycler)
            HomeViewModel.State.EMPTY -> viewSwitcher.display(empty_view)
        }
    }

    private fun onCountryClick(id: String) {
    }
}