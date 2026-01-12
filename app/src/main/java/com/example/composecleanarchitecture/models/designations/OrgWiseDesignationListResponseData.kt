package com.example.composecleanarchitecture.models.designations


import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseData(
    @SerializedName("previousYearQuestions")
    val previousYearQuestions: OrgWiseDesignationListResponsePreviousYearQuestions? = OrgWiseDesignationListResponsePreviousYearQuestions()
)