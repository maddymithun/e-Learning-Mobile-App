package com.example.composecleanarchitecture.data.repository.organization_wise_question

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.models.designations.OrgWiseDesignationListResponse
import com.example.composecleanarchitecture.models.designations.OrgWiseDesignationListResponseAttributesX
import com.example.composecleanarchitecture.models.designations.OrgWiseDesignationListResponseDataX
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.FormatQusAsYearCardModel
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IOrgWiseDesignationRepository : IBaseRepository {

    fun gerOrgWiseDesignations(
        categoryCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<DesignationDataModel>>

    suspend fun gerOrgWiseDesignationsList(
        categoryCode: String,page: Int
    ): OrgWiseDesignationListResponse

    suspend fun gerOrgWiseDesignationsListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<DesignationDataModel>


    fun prevYerQuesByOrgDesgnCode(
        organizationCode: String,
        desigCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>>

    suspend fun getPrevYerQuesByOrgDesgnCodeList(
        organizationCode: String,
        desigCode: String,
        page: Int
    ): PrevYerQusQuizDetailsResponse

    suspend fun getPvsErQusByDesignationOfOrgLiztRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<FormatQuestionsAsExpected>
}