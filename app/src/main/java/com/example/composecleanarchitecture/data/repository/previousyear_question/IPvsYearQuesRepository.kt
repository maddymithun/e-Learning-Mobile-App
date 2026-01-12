package com.example.composecleanarchitecture.data.repository.previousyear_question

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.models.cat_wise_organisations.CatWiseOrganiseNameResponse
import com.example.composecleanarchitecture.models.cat_wise_organisations.PvsErIBAPscOrgName
import com.example.composecleanarchitecture.models.institute_qus_year.CategoryWiseExmYearListResponse
import com.example.composecleanarchitecture.models.institute_qus_year.YearsOfBankInsEduForPvsErQues
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.FormatQusAsYearCardModel
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsDataX
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.models.tomo_sequence.BCSTomoDataFormation
import com.example.composecleanarchitecture.models.tomo_sequence.DataX
import kotlinx.coroutines.flow.Flow

interface IPvsYearQuesRepository : IBaseRepository {

    suspend fun getCategoryTomoSequence(categoryCode: String): Flow<AppNetworkState<ArrayList<DataX>>>
    suspend fun getBcsTomoDataRoom(categoryCode: String): Flow<List<BCSTomoDataFormation>>

    fun getPrevYerQuesBySeqCategoryYear(
        sequence: String,
        categoryCode: String,
        year: Int,
        isApiCall:Boolean
    ): Flow<PagingData<PreviousYearQuestionDetailsFormat>>

    suspend fun getPrevYerQuesBySeqCategoryYearList(
        sequence: String, categoryCode: String, year: Int, page: Int
    ): PrevYerQusQuizDetailsResponse

    suspend fun getBcsAndJudgeQuestionListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<PreviousYearQuestionDetailsFormat>

    suspend fun getCategoryWiseExmYearListResponse(categoryCode: String): Flow<AppNetworkState<CategoryWiseExmYearListResponse>>
    suspend fun getYearListPvsErQuesInsBankIns(categoryCode: String): Flow<List<YearsOfBankInsEduForPvsErQues>>
    suspend fun getOrgNameByCategoryId(categoryCode: String): Flow<AppNetworkState<CatWiseOrganiseNameResponse>>
    suspend  fun getOrgListForIbaPscPvsErRoom(categoryCode: String): Flow<List<PvsErIBAPscOrgName>>

    fun getPrevYerQuesByCategoryYear(
        year: Int,
        categoryCode: String,
        isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>>

    suspend fun getPrevYerQuesByCategoryYearList(
        categoryCode: String, year: Int, page: Int
    ): PrevYerQusQuizDetailsResponse

    suspend fun getPrevYerQuesByCategoryYearListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<FormatQuestionsAsExpected>

    fun PrevYerQuesByOrganizationCode(
        stringOrganizationCode: String,
        categoryCode: String,
        isApiCall:Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>>

    suspend fun getPrevYerQuesByOrganizationCodeList(
        stringOrganizationCode: String,
        categoryCode: String, page: Int
    ): PrevYerQusQuizDetailsResponse


}