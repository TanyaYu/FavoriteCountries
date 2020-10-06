package com.tanyaiuferova.favoritecountries.di.activity

import com.tanyaiuferova.favoritecountries.ui.CountrySearchFragment
import com.tanyaiuferova.favoritecountries.ui.HomeFragment
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
}