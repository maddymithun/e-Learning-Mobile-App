package com.example.composecleanarchitecture.data.repository.organization_wise_question

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.GetDesignationsByOrgCodeQuery
import com.example.composecleanarchitecture.GetPrevErQusByOrgDesgCodeQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.models.designations.OrgWiseDesignationListResponse
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.paging.OrgWiseDesigPagingDataSource
import com.example.composecleanarchitecture.paging.PvsErQusByDesgPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrgWiseDesignationRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : IOrgWiseDesignationRepository, BaseRepository() {
    override fun gerOrgWiseDesignations(
        categoryCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<DesignationDataModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            OrgWiseDesigPagingDataSource(
                categoryCode = categoryCode,
                repository = this,
                roomHelper,
                preferencesHelper,
                isApiCall
            )
        }.flow
    }

    override suspend fun gerOrgWiseDesignationsList(
        categoryCode: String,
        page: Int
    ): OrgWiseDesignationListResponse {
        try {
            val response = apolloClient.query(
                GetDesignationsByOrgCodeQuery(
                    categoryCode = categoryCode,
                    page = page
                )
            ).execute()
            val tomoResponse = apolloResponseToRetrofitResponse(
                response
            )
            return tomoResponse.convertData()
        } catch (e: Exception) {
            return apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override suspend fun gerOrgWiseDesignationsListRoom(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<DesignationDataModel> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getDesignationOfOrg(queryCode, limit, offset)
    }

    override fun prevYerQuesByOrgDesgnCode(
        organizationCode: String,
        desigCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PvsErQusByDesgPagingDataSource(
                organizationCode = organizationCode,
                designationCode = desigCode,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                repository = this,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getPrevYerQuesByOrgDesgnCodeList(
        organizationCode: String,
        desigCode: String,
        page: Int
    ): PrevYerQusQuizDetailsResponse {
        try {
            val response = apolloClient.query(
                GetPrevErQusByOrgDesgCodeQuery(
                    orgCode = organizationCode,
                    desgCode = desigCode,
                    page = page
                )
            ).execute()
            val yearResponse = apolloResponseToRetrofitResponse(
                response
            )
            return yearResponse.convertData()
        } catch (e: Exception) {
            return apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override suspend fun getPvsErQusByDesignationOfOrgLiztRoom(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<FormatQuestionsAsExpected> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getPvsErQuestionAsExpectedFormat(queryCode = queryCode, limit = limit, offset = offset)
    }


}
