package com.example.composecleanarchitecture.data.repository.topics_wise

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.GetAllSubjectsQuery
import com.example.composecleanarchitecture.GetPrevErQuesBySubTopicsQuery
import com.example.composecleanarchitecture.GetSubTopicsQuery
import com.example.composecleanarchitecture.GetSubjectWiseTopicsQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.SubjectListResponse
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponse
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.sub_topics.SubjectSubtopicsResponse
import com.example.composecleanarchitecture.paging.PvsErQuesBySubTopicsPagingSource
import com.example.composecleanarchitecture.paging.SubTopicsListPagingDataSource
import com.example.composecleanarchitecture.paging.SubjectListPagingDataSource
import com.example.composecleanarchitecture.paging.TopicsListPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopicsRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : ITopicsRepository, BaseRepository() {

    override fun getALlSubjects(
        categoryName: String,
        isApiCall: Boolean
    ): Flow<PagingData<SubjectListResponseDataX>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SubjectListPagingDataSource(
                repository = this,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getAllSubjectList(page: Int): SubjectListResponse {
        try {
            val response = apolloClient.query(
                GetAllSubjectsQuery(
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

    override suspend fun getAllSubjectListRoom(
        limit: Int,
        offset: Int
    ): List<SubjectListResponseDataX> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getDashSubjectListCategory(limit = limit, offset = offset)
    }

    override fun getSubjectWiseTopics(
        subjectCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<TopicsListResponseDataX>> {
        return Pager(PagingConfig(pageSize = 20)) {
            TopicsListPagingDataSource(
                subjectCode = subjectCode,
                repository = this,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getSubjectWiseTopicsList(
        subjectCode: String,
        page: Int
    ): TopicsListResponse {
        try {
            val response = apolloClient.query(
                GetSubjectWiseTopicsQuery(
                    subjectCode = subjectCode,
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

    override suspend fun getSubjectWiseTopicsListRoom(
        subjectCode: String,
        limit: Int,
        offset: Int
    ): List<TopicsListResponseDataX> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getTopicsOfSubject(code = subjectCode, limit = limit, offset = offset)
    }


    override fun getSubTopics(
        subjectCode: String,
        topicsCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<SubTopicsListResponseDataX>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SubTopicsListPagingDataSource(
                topicsCode = topicsCode,
                subjectCode = subjectCode,
                repository = this,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getSubTopicsList(
        subTopicsCode: String,
        page: Int
    ): SubjectSubtopicsResponse {
        try {
            val response = apolloClient.query(
                GetSubTopicsQuery(
                    topicsCode = subTopicsCode,
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

    override suspend fun getSubtopicsOfTopicsOfSubjectRoom(
        subjectCode: String,
        subtopicsCode: String,
        limit: Int,
        offset: Int
    ): List<SubTopicsListResponseDataX> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getSubtopicsOfTopicsAndSubject(
                subjectCode = subjectCode,
                topicsCode = subtopicsCode,
                limit = limit,
                offset = offset
            )
    }

    override fun getSubTopicsPvsErQues(
        subtopicsCode: String,
        queryCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PvsErQuesBySubTopicsPagingSource(
                subtopicsCode = subtopicsCode,
                queryCode = queryCode,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                repository = this,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getSubTopicsPvsErQuesList(
        subtopicsCode: String,
        page: Int
    ): PrevYerQusQuizDetailsResponse {
        try {

            val response = apolloClient.query(
                GetPrevErQuesBySubTopicsQuery(
                    subtopicCode = subtopicsCode,
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

    override suspend fun getSubTopicsPvsErQuesListRoom(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<FormatQuestionsAsExpected> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getPvsErQuestionAsExpectedFormat(queryCode = queryCode, limit = limit, offset = offset)
    }
}