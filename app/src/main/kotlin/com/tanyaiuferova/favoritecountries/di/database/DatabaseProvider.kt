package com.tanyaiuferova.favoritecountries.di.database

import android.content.Context
import androidx.room.Room
import com.tanyaiuferova.favoritecountries.data.AppDatabase
import javax.inject.Provider

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
class DatabaseProvider(private val context: Context) : Provider<AppDatabase> {
    override fun get(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "countries"
        ).build()
    }
}