package com.tanyaiuferova.favoritecountries.ui.countrydetails

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.DataBinding

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class CountryDetailsDataBinding : DataBinding<CountryDetailsDataBinding.ViewHolder>() {

    var item by binding(CountryDetailsItem.empty()) { value ->
        toolbar.title = value.title
        notesEditText.setText(value.notes)
    }

    override fun createViewHolder(view: View) = ViewHolder(view)

    class ViewHolder(private val view: View) : DataBinding.ViewHolder(view) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val notesEditText = view.findViewById<TextInputEditText>(R.id.notes_et)
    }
}