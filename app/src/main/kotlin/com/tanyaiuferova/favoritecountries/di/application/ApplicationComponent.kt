package com.tanyaiuferova.favoritecountries.di.application

import com.tanyaiuferova.favoritecountries.Application
import com.tanyaiuferova.favoritecountries.di.database.DatabaseModule
import com.tanyaiuferova.favoritecountries.di.network.NetworkModule
import com.tanyaiuferova.favoritecountries.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Author: Tanya Iuferova
 * Date: 10/5/2020
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<Application> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}