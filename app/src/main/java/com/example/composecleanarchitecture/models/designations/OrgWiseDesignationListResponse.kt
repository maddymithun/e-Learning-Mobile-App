package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponse(
    @SerializedName("data")
    val `data`: OrgWiseDesignationListResponseData? = OrgWiseDesignationListResponseData()
)