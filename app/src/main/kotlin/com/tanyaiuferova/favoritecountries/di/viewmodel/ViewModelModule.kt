package com.tanyaiuferova.favoritecountries.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanyaiuferova.favoritecountries.viewmodels.CountrySearchViewModel
import com.tanyaiuferova.favoritecountries.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Author: Tanya Iuferova
 * Date: 10/5/2020
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountrySearchViewModel::class)
    internal abstract fun bindCountrySearchViewModel(viewModel: CountrySearchViewModel): ViewModel

}