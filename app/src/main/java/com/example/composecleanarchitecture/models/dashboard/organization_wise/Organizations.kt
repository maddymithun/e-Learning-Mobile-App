package com.example.composecleanarchitecture.models.dashboard.organization_wise


import com.google.gson.annotations.SerializedName

data class Organizations(
    @SerializedName("data")
    val `data`: List<DataX>? = listOf()
)