package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseDesignation(
    @SerializedName("data")
    val `data`: OrgWiseDesignationListResponseDataXX? = OrgWiseDesignationListResponseDataXX()
)