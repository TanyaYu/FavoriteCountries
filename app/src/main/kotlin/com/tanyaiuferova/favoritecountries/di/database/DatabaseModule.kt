package com.tanyaiuferova.favoritecountries.di.database

import android.content.Context
import com.tanyaiuferova.favoritecountries.data.AppDatabase
import com.tanyaiuferova.favoritecountries.data.country.CountriesDao
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
    fun provideDatabase(context: Context): AppDatabase = DatabaseProvider(context).get()

    @Provides
    @Singleton
    @JvmStatic
    fun provideCountriesDao(database: AppDatabase): CountriesDao = database.countriesDao()
}