package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseMeta(
    @SerializedName("pagination")
    val pagination: OrgWiseDesignationListResponsePagination? = OrgWiseDesignationListResponsePagination()
)