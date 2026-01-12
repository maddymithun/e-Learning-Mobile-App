package com.example.composecleanarchitecture.models.dashboard.previous_year


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = ""
)