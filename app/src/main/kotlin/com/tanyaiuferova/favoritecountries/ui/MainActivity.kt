package com.tanyaiuferova.favoritecountries.ui

import android.os.Bundle
import com.tanyaiuferova.favoritecountries.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}