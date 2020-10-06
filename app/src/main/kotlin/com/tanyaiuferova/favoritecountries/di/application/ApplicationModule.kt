package com.tanyaiuferova.favoritecountries.di.application

import android.content.Context
import com.tanyaiuferova.favoritecountries.Application
import com.tanyaiuferova.favoritecountries.di.activity.ActivityScope
import com.tanyaiuferova.favoritecountries.di.activity.FragmentBuildersModule
import com.tanyaiuferova.favoritecountries.di.activity.MainActivityModule
import com.tanyaiuferova.favoritecountries.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Tanya Iuferova
 * Date: 10/5/2020
 */
@Module
abstract class ApplicationModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuildersModule::class])
    @ActivityScope
    internal abstract fun bindMainActivity(): MainActivity
}