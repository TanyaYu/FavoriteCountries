package com.tanyaiuferova.favoritecountries.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.utils.Schedulers.main
import com.tanyaiuferova.favoritecountries.viewmodels.HomeViewModel
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    private val dataBinding = HomeDataBinding()

    private val adapter = FavoriteCountriesAdapter(
        onItemClick = ::onCountryClick
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.onViewCreated(view)

        with(recycler) {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        add_btn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToSearch())
        }
    }

    override fun onAttached() {
        disposable += viewModel.favorites
            .observeOn(main)
            .subscribeBy(onNext = ::bindFavorites)

        disposable += viewModel.state
            .observeOn(main)
            .subscribeBy(onNext = { dataBinding.state = it })
    }

    private fun bindFavorites(list: List<FavoriteCountryItem>) {
        adapter.submitList(list)
    }

    private fun onCountryClick(id: String) {
        Snackbar.make(container, R.string.home_on_delete_message, Snackbar.LENGTH_LONG)
            .setAction(R.string.action_yes) { viewModel.deleteFromFavorites(id) }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding.onDestroyView()
    }

    enum class State {
        DATA,
        EMPTY
    }
}