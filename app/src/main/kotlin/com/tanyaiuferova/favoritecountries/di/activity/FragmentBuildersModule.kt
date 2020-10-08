package com.tanyaiuferova.favoritecountries.di.activity

import com.tanyaiuferova.favoritecountries.ui.countrydetails.CountryDetailsFragment
import com.tanyaiuferova.favoritecountries.ui.countrysearch.CountrySearchFragment
import com.tanyaiuferova.favoritecountries.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Tanya Yuferova
 * Date: 10/5/2020
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindCountrySearchFragment(): CountrySearchFragment

    @ContributesAndroidInjector
    internal abstract fun bindCountryDetailsFragment(): CountryDetailsFragment
}