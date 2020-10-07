package com.tanyaiuferova.favoritecountries.viewmodels

import androidx.lifecycle.ViewModel
import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import javax.inject.Inject

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
class HomeViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {
    val countries = countriesRepository.getAllCountries(1)
}