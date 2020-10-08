package com.tanyaiuferova.favoritecountries.utils

import androidx.appcompat.widget.SearchView

/**
 * Author: Tanya Yuferova
 * Date: 10/7/2020
 */
fun SearchView.setOnQueryListener(
    onQueryTextSubmit: ((query: String?) -> Unit)? = null,
    onQueryTextChange: ((newText: String?) -> Unit)? = null
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if(onQueryTextSubmit == null) return false
            else {
                onQueryTextSubmit(query)
                return true
            }
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if(onQueryTextChange == null) return false
            else {
                onQueryTextChange(newText)
                return true
            }
        }
    })
}