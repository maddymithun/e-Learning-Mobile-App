package com.example.composecleanarchitecture.data.repository.topics_wise

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.SubjectListResponse
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponse
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.sub_topics.SubjectSubtopicsResponse
import kotlinx.coroutines.flow.Flow

interface ITopicsRepository : IBaseRepository {

    fun getALlSubjects(
        categoryName: String, isApiCall: Boolean
    ): Flow<PagingData<SubjectListResponseDataX>>

    suspend fun getAllSubjectList(
        page: Int
    ): SubjectListResponse

    suspend fun getAllSubjectListRoom(
        limit: Int, offset: Int
    ): List<SubjectListResponseDataX>


    fun getSubjectWiseTopics(
        subjectCode: String, isApiCall: Boolean
    ): Flow<PagingData<TopicsListResponseDataX>>

    suspend fun getSubjectWiseTopicsList(
        subjectCode: String, page: Int
    ): TopicsListResponse

    suspend fun getSubjectWiseTopicsListRoom(
        subjectCode: String, limit: Int, offset: Int
    ): List<TopicsListResponseDataX>


    fun getSubTopics(
        subjectCode: String, topicsCode: String, isApiCall: Boolean
    ): Flow<PagingData<SubTopicsListResponseDataX>>

    suspend fun getSubTopicsList(
        subTopicsCode: String, page: Int
    ): SubjectSubtopicsResponse

    suspend fun getSubtopicsOfTopicsOfSubjectRoom(
        subjectCode: String, subtopicsCode: String, limit: Int, offset: Int
    ): List<SubTopicsListResponseDataX>

    fun getSubTopicsPvsErQues(
        subtopicsCode: String, queryCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>>

    suspend fun getSubTopicsPvsErQuesList(
        subtopicsCode: String, page: Int
    ): PrevYerQusQuizDetailsResponse

    suspend fun getSubTopicsPvsErQuesListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<FormatQuestionsAsExpected>

}