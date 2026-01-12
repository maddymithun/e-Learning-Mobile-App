package com.example.composecleanarchitecture.data.repository.year_wise_question

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IYearWiseQuestRepository : IBaseRepository {

    fun getPrevErQuesByYear(
        year: Int,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>>

    suspend fun getPrevErQuesByYearList(
        year: Int, page: Int
    ): PrevYerQusQuizDetailsResponse

    suspend fun getPrevErQuesByYearListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<FormatQuestionsAsExpected>
}