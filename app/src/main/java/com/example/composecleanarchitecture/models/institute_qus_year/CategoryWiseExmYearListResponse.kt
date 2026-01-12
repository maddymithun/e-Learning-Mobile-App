package com.example.composecleanarchitecture.models.institute_qus_year


import com.google.gson.annotations.SerializedName

data class CategoryWiseExmYearListResponse(
    @SerializedName("data")
    val `data`: CategoryWiseExmYearListData? = CategoryWiseExmYearListData()
)