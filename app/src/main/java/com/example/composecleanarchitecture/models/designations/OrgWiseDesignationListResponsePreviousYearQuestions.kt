package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponsePreviousYearQuestions(
    @SerializedName("data")
    val `data`: List<OrgWiseDesignationListResponseDataX>? = listOf(),
    @SerializedName("meta")
    val meta: OrgWiseDesignationListResponseMeta? = OrgWiseDesignationListResponseMeta()
)