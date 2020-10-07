package com.tanyaiuferova.favoritecountries.ui.countrysearch

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.pagination.PaginationAdapter
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel.Companion.PAGE_SIZE
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_country_search.*
import timber.log.Timber

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountrySearchFragment : BaseFragment(R.layout.fragment_country_search) {

    private val viewModel by viewModels<CountrySearchViewModel>()

    private val adapter = CountrySearchAdapter(
        onItemClick = ::onCountryClick
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        countries_rv.adapter = PaginationAdapter(
            PAGE_SIZE,
            viewModel::onNewPageRequest,
            adapter
        )
        countries_rv.layoutManager = LinearLayoutManager(requireContext())
        countries_rv.setHasFixedSize(true)
    }

    override fun onAttached() {
        disposable += viewModel.countries.subscribeBy(
            onNext = ::bindCountriesList,
            onError = ::onLoadError
        )
    }

    private fun bindCountriesList(markets: List<CountrySearchItem>) {
        adapter.submitList(markets)
    }

    private fun onLoadError(throwable: Throwable) {
        Timber.e(throwable)
        //TODO notify user about the error
    }

    private fun onCountryClick(id: String) {
    }
}