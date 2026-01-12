package com.example.composecleanarchitecture.models.dashboard.organization_wise


import com.google.gson.annotations.SerializedName

data class DashOrgWiseCategoryResponse(
    @SerializedName("data")
    val `data`: Data? = Data()
)