package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponsePagination(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("pageCount")
    val pageCount: Int? = 0,
    @SerializedName("pageSize")
    val pageSize: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)