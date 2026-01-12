package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseAttributes(
    @SerializedName("designation")
    val designation: OrgWiseDesignationListResponseDesignation? = OrgWiseDesignationListResponseDesignation()
)