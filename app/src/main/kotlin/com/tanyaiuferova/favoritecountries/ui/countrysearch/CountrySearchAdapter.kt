package com.tanyaiuferova.favoritecountries.ui.countrysearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanyaiuferova.favoritecountries.R

/**
 * Author: Tanya Yuferova
 * Date: 10/7/2020
 */
class CountrySearchAdapter(private val onItemClick: (String) -> Unit = {}) :
    ListAdapter<CountrySearchItem, CountrySearchAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<CountrySearchItem>() {
            override fun areItemsTheSame(old: CountrySearchItem, new: CountrySearchItem): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(
                old: CountrySearchItem,
                new: CountrySearchItem
            ): Boolean {
                return old == new
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            name.text = item.name
            itemView.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_tv)
    }
}