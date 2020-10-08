package com.tanyaiuferova.favoritecountries.ui.countrysearch

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.pagination.PaginationAdapter
import com.tanyaiuferova.favoritecountries.ui.base.BaseFragment
import com.tanyaiuferova.favoritecountries.ui.views.ViewSwitcher
import com.tanyaiuferova.favoritecountries.utils.Schedulers
import com.tanyaiuferova.favoritecountries.utils.setOnQueryListener
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel.Companion.PAGE_SIZE
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel.State.*
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_country_search.*
import kotlin.properties.Delegates

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class CountrySearchFragment : BaseFragment(R.layout.fragment_country_search) {

    private val viewModel by viewModels<CountrySearchViewModel>()

    private val adapter = CountrySearchAdapter(
        onItemClick = ::onCountryClick
    )

    private val paginationAdapter by lazy {
        PaginationAdapter(
            PAGE_SIZE,
            viewModel::onNewPageRequest,
            adapter
        )
    }

    private lateinit var viewSwitcher: ViewSwitcher

    //TODO implement DataBinding
    private var state by Delegates.observable(DATA) { _, _, newValue ->
        if (view != null) bindState(newValue)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewSwitcher = ViewSwitcher(
            progress_bar,
            recycler,
            error_view,
            empty_view
        )
        with(recycler) {
            adapter = paginationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        with(toolbar) {
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            inflateMenu(R.menu.country_search)
            val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
            searchView.setOnQueryListener(
                onQueryTextChange = { newText ->
                    viewModel.onQueryChanged(newText.orEmpty())
                }
            )
        }

        retry_btn.setOnClickListener {
            viewModel.onRetryClick()
        }
        bindState(state)
    }

    override fun onAttached() {
        disposable += viewModel.countries.subscribeBy(onNext = ::bindCountriesList)
        disposable += viewModel.state
            .observeOn(Schedulers.main)
            .subscribeBy(onNext = { state = it })
    }

    private fun bindCountriesList(markets: List<CountrySearchItem>) {
        paginationAdapter.submitList(markets)
    }

    private fun bindState(state: CountrySearchViewModel.State) {
        when (state) {
            LOADING -> viewSwitcher.display(progress_bar)
            DATA -> viewSwitcher.display(recycler)
            ERROR -> viewSwitcher.display(error_view)
            EMPTY -> viewSwitcher.display(empty_view)
            PAGE_ERROR -> showPageErrorMessage()
        }
    }

    private fun showPageErrorMessage() {
        Snackbar.make(
            container,
            R.string.country_search_error_message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun onCountryClick(id: String) {
        findNavController().navigate(CountrySearchFragmentDirections.actionSearchToDetails(id))
    }
}