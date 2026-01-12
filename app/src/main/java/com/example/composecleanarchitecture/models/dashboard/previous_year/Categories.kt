package com.example.composecleanarchitecture.models.dashboard.previous_year


import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("data")
    val `data`: List<DataX>? = listOf()
)