package com.example.composecleanarchitecture.data.repository.year_wise_question

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.PreviousYearQuestionsByYearQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.paging.PrevErQuesByYearPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YearWiseQuestionRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : IYearWiseQuestRepository, BaseRepository() {
    override fun getPrevErQuesByYear(
        year: Int,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PrevErQuesByYearPagingDataSource(
                year = year,
                repository = this,
                isApiCall = isApiCall,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper
            )
        }.flow
    }

    override suspend fun getPrevErQuesByYearList(
        year: Int,
        page: Int
    ): PrevYerQusQuizDetailsResponse {
        try {
            val response = apolloClient.query(
                PreviousYearQuestionsByYearQuery(
                    year = year,
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

    override suspend fun getPrevErQuesByYearListRoom(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<FormatQuestionsAsExpected> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getPvsErQuestionAsExpectedFormat(queryCode = queryCode, limit = limit, offset = offset)

    }

}