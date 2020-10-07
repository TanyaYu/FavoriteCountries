package com.tanyaiuferova.favoritecountries.data.country

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: Tanya Yuferova
 * Date: 10/6/2020
 */
@Entity(tableName = "country")
data class Country(
    @PrimaryKey
    val id: String,
    val name: String,
    val isFavorite: Boolean = false,
    val notes: String? = null
)