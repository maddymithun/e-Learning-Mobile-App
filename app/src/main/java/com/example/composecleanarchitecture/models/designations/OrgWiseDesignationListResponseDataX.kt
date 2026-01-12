package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseDataX(
    @SerializedName("attributes")
    val attributes: OrgWiseDesignationListResponseAttributes? = OrgWiseDesignationListResponseAttributes(),
    @SerializedName("id")
    val id: String? = ""
)