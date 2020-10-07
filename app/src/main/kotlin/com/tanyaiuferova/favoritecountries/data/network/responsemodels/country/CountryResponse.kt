package com.tanyaiuferova.favoritecountries.data.network.responsemodels.country


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("capitalCity")
    val capitalCity: String?
)