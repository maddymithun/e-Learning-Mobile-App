package com.example.composecleanarchitecture.data.repository.courses

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.GetAllCoursesQuery
import com.example.composecleanarchitecture.GetCourseQuestionsQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscResponse
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponse
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX
import com.example.composecleanarchitecture.paging.CourseQuestionPagingDataSource
import com.example.composecleanarchitecture.paging.DashCoursePagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : ICourseRepository, BaseRepository() {
    override fun getALlCourse(): Flow<PagingData<CourseListWithTopiscDataX>> {
        return Pager(PagingConfig(pageSize = 20)) {
            DashCoursePagingDataSource(
                repository = this
            )
        }.flow
    }

    override suspend fun getAllCourseList(page: Int): CourseListWithTopiscResponse {
        return try {
            val response = apolloClient.query(
                GetAllCoursesQuery(
                    page = page
                )
            ).execute()
            val tomoResponse = apolloResponseToRetrofitResponse(
                response
            )
            tomoResponse.convertData()
        } catch (e: Exception) {
            apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override fun getCourseQuestions(courseCode: Int): Flow<PagingData<CourseQuestionListResponseDataX>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CourseQuestionPagingDataSource(
                repository = this,
                courseCode = courseCode
            )
        }.flow
    }

    override suspend fun getCourseQuestionsList(
        courseCode: Int,
        page: Int
    ): CourseQuestionListResponse {
        try {
            val response = apolloClient.query(
                GetCourseQuestionsQuery(
                    courseCode,
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

}