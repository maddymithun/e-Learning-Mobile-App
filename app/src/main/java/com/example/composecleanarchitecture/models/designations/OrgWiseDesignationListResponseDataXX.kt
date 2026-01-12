package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseDataXX(
    @SerializedName("attributes")
    val attributes: OrgWiseDesignationListResponseAttributesX? = OrgWiseDesignationListResponseAttributesX(),
    @SerializedName("id")
    val id: String? = ""
)