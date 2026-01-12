package com.example.composecleanarchitecture.data.repository.courses

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscResponse
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponse
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX
import kotlinx.coroutines.flow.Flow

interface ICourseRepository : IBaseRepository {

    fun getALlCourse(
    ): Flow<PagingData<CourseListWithTopiscDataX>>

    suspend fun getAllCourseList(
        page: Int
    ): CourseListWithTopiscResponse

    fun getCourseQuestions(
        courseCode: Int
    ): Flow<PagingData<CourseQuestionListResponseDataX>>

    suspend fun getCourseQuestionsList(
        courseCode: Int,
        page: Int
    ): CourseQuestionListResponse
}