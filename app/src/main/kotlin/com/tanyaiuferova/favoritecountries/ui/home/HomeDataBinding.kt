package com.tanyaiuferova.favoritecountries.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.DataBinding
import com.tanyaiuferova.favoritecountries.ui.home.HomeFragment.State.*
import com.tanyaiuferova.favoritecountries.ui.views.ViewSwitcher

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class HomeDataBinding: DataBinding<HomeDataBinding.ViewHolder>() {

    var state by binding(DATA) { value ->
        when (value) {
            DATA -> viewSwitcher.display(recycler)
            EMPTY -> viewSwitcher.display(emptyView)
        }
    }

    override fun createViewHolder(view: View) = ViewHolder(view)

    class ViewHolder(private val view: View) : DataBinding.ViewHolder(view) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val emptyView = view.findViewById<TextView>(R.id.empty_view)
        val viewSwitcher = ViewSwitcher(recycler, emptyView)
    }
}