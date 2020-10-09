package com.tanyaiuferova.favoritecountries.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView

/**
 * Author: Tanya Yuferova
 * Date: 10/7/2020
 */
inline fun SearchView.setOnQueryListener(
    crossinline onQueryTextSubmit: ((query: String?) -> Boolean) = { _ -> false },
    crossinline onQueryTextChange: ((newText: String?) -> Boolean) = { _ -> false }
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return onQueryTextSubmit(query)
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return onQueryTextChange(newText)
        }
    })
}

fun View.showSoftKeyboard() {
    if (requestFocus()) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideSoftKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}