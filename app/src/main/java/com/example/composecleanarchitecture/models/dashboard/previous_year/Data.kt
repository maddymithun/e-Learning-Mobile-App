package com.example.composecleanarchitecture.models.dashboard.previous_year


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("categories")
    val categories: Categories? = Categories()
)