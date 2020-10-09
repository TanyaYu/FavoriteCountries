package com.tanyaiuferova.favoritecountries.ui.countrydetails

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tanyaiuferova.favoritecountries.R
import com.tanyaiuferova.favoritecountries.ui.base.DataBinding

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class CountryDetailsDataBinding : DataBinding<CountryDetailsDataBinding.ViewHolder>() {

    var title by binding("") { value ->
        toolbar.title = value
        notesTextInput.hint = view.resources.getString(
            R.string.country_details_notes_hint,
            value
        )
    }

    var notes by binding("") { value ->
        notesEditText.setText(value)
        notesEditText.setSelection(value.length)
    }

    override fun createViewHolder(view: View) = ViewHolder(view)

    class ViewHolder(val view: View) : DataBinding.ViewHolder(view) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val notesEditText = view.findViewById<TextInputEditText>(R.id.notes_et)
        val notesTextInput = view.findViewById<TextInputLayout>(R.id.notes_input)
    }
}