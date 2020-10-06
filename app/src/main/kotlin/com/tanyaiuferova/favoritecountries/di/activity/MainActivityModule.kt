package com.tanyaiuferova.favoritecountries.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.tanyaiuferova.favoritecountries.ui.MainActivity
import dagger.Binds
import dagger.Module

/**
 * Author: Tanya Iuferova
 * Date: 10/5/2020
 */
@Module
abstract class MainActivityModule {

    @Binds
    internal abstract fun provideMainActivity(activity: MainActivity): AppCompatActivity
}