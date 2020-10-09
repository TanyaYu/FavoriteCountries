package com.tanyaiuferova.favoritecountries.ui.countrysearch

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.DataBinding
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchFragment.State.*
import com.tanyaiuferova.favoritecountries.ui.views.ViewSwitcher

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class CountrySearchBinding: DataBinding<CountrySearchBinding.ViewHolder>() {

    var state by binding(DATA) { value ->
        when (value) {
            LOADING -> viewSwitcher.display(progressBar)
            DATA -> viewSwitcher.display(recycler)
            ERROR -> viewSwitcher.display(errorView)
            EMPTY -> viewSwitcher.display(emptyView)
            PAGE_ERROR -> showPageErrorMessage(view)
        }
    }

    private fun showPageErrorMessage(view: View) {
        Snackbar.make(
            view,
            R.string.country_search_error_message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        // FIXME!!! Shown call binding in the base class
        with (viewHolder!!) {
            when (state) {
                LOADING -> viewSwitcher.display(progressBar)
                DATA -> viewSwitcher.display(recycler)
                ERROR -> viewSwitcher.display(errorView)
                EMPTY -> viewSwitcher.display(emptyView)
                PAGE_ERROR -> showPageErrorMessage(view)
            }
        }
    }

    override fun createViewHolder(view: View) = ViewHolder(view)

    class ViewHolder(val view: View) : DataBinding.ViewHolder(view) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val emptyView = view.findViewById<View>(R.id.empty_view)
        val errorView = view.findViewById<View>(R.id.error_view)
        val progressBar = view.findViewById<View>(R.id.progress_bar)
        val viewSwitcher = ViewSwitcher(
            recycler,
            progressBar,
            emptyView,
            errorView
        )
    }
}