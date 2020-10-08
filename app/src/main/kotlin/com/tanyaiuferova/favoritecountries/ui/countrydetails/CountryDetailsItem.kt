package com.tanyaiuferova.favoritecountries.ui.countrydetails

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
data class CountryDetailsItem(
    val id: String,
    val title: String,
    val notes: String
) {
    companion object {
        fun empty() = CountryDetailsItem("", "", "")
    }
}
