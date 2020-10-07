package com.tanyaiuferova.favoritecountries.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tanyaiuferova.favoritecountries.data.country.CountriesDao
import com.tanyaiuferova.favoritecountries.data.country.Country

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
@Database(
    entities = [Country::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}