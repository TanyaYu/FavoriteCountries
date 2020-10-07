package com.tanyaiuferova.favoritecountries.data.network.responsemodels


import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("per_page")
    val perPage: String,
    @SerializedName("total")
    val total: Int
)