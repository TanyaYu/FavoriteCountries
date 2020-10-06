package com.tanyaiuferova.favoritecountries.di.database

import com.tanyaiuferova.favoritecountries.data.country.CountriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Tanya Iuferova
 * Date: 10/5/2020
 */
@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideCountriesRepository(): CountriesRepository {
        return CountriesRepository()
    }
}