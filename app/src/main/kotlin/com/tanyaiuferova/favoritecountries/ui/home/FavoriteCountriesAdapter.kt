package com.tanyaiuferova.favoritecountries.ui.home

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
 * Date: 10/8/2020
 */
class FavoriteCountriesAdapter(private val onItemClick: (String) -> Unit = {}) :
    ListAdapter<FavoriteCountryItem, FavoriteCountriesAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<FavoriteCountryItem>() {
            override fun areItemsTheSame(
                old: FavoriteCountryItem,
                new: FavoriteCountryItem
            ): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(
                old: FavoriteCountryItem,
                new: FavoriteCountryItem
            ): Boolean {
                return old == new
            }
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            title.text = item.title
            notes.text = item.notes
            itemView.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_tv)
        val notes: TextView = view.findViewById(R.id.notes_tv)
    }
}