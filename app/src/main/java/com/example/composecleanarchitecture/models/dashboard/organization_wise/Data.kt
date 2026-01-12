package com.example.composecleanarchitecture.models.dashboard.organization_wise


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("organizations")
    val organizations: Organizations? = Organizations()
)