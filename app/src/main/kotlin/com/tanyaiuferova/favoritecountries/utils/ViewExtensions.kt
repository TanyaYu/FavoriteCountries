package com.tanyaiuferova.favoritecountries.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun View.showSoftKeyboard() {
    if (requestFocus()) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideSoftKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}