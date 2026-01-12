package com.example.composecleanarchitecture.data.repository.previousyear_question

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.GetOrgNameByCategoryIdQuery
import com.example.composecleanarchitecture.PrevYerQuesByCategoryYearQuery
import com.example.composecleanarchitecture.PrevYerQuesByOrganizationCodeQuery
import com.example.composecleanarchitecture.PrevYerQuesBySeqCategoryQuery
import com.example.composecleanarchitecture.SequenceOfExamTomoQuery
import com.example.composecleanarchitecture.YearsOfPvsYrCategoryByInstQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.cat_wise_organisations.CatWiseOrganiseNameResponse
import com.example.composecleanarchitecture.models.cat_wise_organisations.PvsErIBAPscOrgName
import com.example.composecleanarchitecture.models.institute_qus_year.CategoryWiseExmYearListResponse
import com.example.composecleanarchitecture.models.institute_qus_year.YearsOfBankInsEduForPvsErQues
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerQusQuizDetailsResponse
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.models.tomo_sequence.BCSTomoDataFormation
import com.example.composecleanarchitecture.models.tomo_sequence.DataX
import com.example.composecleanarchitecture.models.tomo_sequence.TomoBcsSequenceResponse
import com.example.composecleanarchitecture.paging.BankEduInsPvsErQuesPagingSource
import com.example.composecleanarchitecture.paging.PrevErQusByOrgPagingSource
import com.example.composecleanarchitecture.paging.PrevYerBcsPagingSource
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class PvsYearQuesRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : IPvsYearQuesRepository, BaseRepository() {

    override suspend fun getCategoryTomoSequence(categoryCode: String): Flow<AppNetworkState<ArrayList<DataX>>> {
        try {
            var currentIndex = 0
            var totalIndex = Int.MAX_VALUE
            val tomoSequenceList = ArrayList<DataX>()
            while (currentIndex <= totalIndex) {
                val response = apolloClient.query(
                    SequenceOfExamTomoQuery(exmCode = categoryCode, pageNumber = currentIndex)
                ).execute()
                val tomoResponse: TomoBcsSequenceResponse = apolloResponseToRetrofitResponse(
                    response
                ).convertJson()
                tomoResponse.data?.previousYearQuestions?.data?.let { tomoSequenceList.addAll(it.distinctBy { its -> its.attributes?.sequence }) }
                currentIndex++
                totalIndex = tomoResponse.data?.previousYearQuestions?.meta?.pagination?.pageCount!!
            }

            return apiCallingState { tomoSequenceList }
        } catch (e: Exception) {
            return apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override suspend fun getBcsTomoDataRoom(categoryCode: String): Flow<List<BCSTomoDataFormation>> {
        return roomHelper.getRoomDbInstance().questionCategoryDao().getBcsTomoDataList(categoryCode)
    }

    override fun getPrevYerQuesBySeqCategoryYear(
        sequence: String, categoryCode: String, year: Int, isApiCall: Boolean
    ): Flow<PagingData<PreviousYearQuestionDetailsFormat>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PrevYerBcsPagingSource(
                sequence = sequence,
                categoryCode = categoryCode,
                year = year,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                repository = this,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getPrevYerQuesBySeqCategoryYearList(
        sequence: String, categoryCode: String, year: Int, page: Int
    ): PrevYerQusQuizDetailsResponse {
        return try {
            val response = apolloClient.query(
                PrevYerQuesBySeqCategoryQuery(
                    sequence = sequence, categoryCode = categoryCode, page = page
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

    override suspend fun getBcsAndJudgeQuestionListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<PreviousYearQuestionDetailsFormat> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getBcsAndJudgeQuestionList(categoryCode = queryCode, limit = limit, offset = offset)
    }

    override suspend fun getCategoryWiseExmYearListResponse(categoryCode: String): Flow<AppNetworkState<CategoryWiseExmYearListResponse>> {
        return try {
            val response = apolloClient.query(
                YearsOfPvsYrCategoryByInstQuery(categoryCode)
            ).execute()
            apiCallingState {
                apolloResponseToRetrofitResponse(
                    response
                ).convertData()
            }
        } catch (e: Exception) {
            apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override suspend fun getYearListPvsErQuesInsBankIns(categoryCode: String): Flow<List<YearsOfBankInsEduForPvsErQues>> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getYearListPvsErQuesInsBankIns(categoryCode)
    }

    override suspend fun getOrgListForIbaPscPvsErRoom(categoryCode: String): Flow<List<PvsErIBAPscOrgName>> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getOrgListForIbaPscPvsEr(categoryCode)
    }

    override suspend fun getOrgNameByCategoryId(categoryCode: String): Flow<AppNetworkState<CatWiseOrganiseNameResponse>> {
        try {
            val response = apolloClient.query(
                GetOrgNameByCategoryIdQuery(categoryCode)
            ).execute()
            return apiCallingState {
                apolloResponseToRetrofitResponse(
                    response
                ).convertData()
            }
        } catch (e: Exception) {
            return apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override fun getPrevYerQuesByCategoryYear(
        year: Int, categoryCode: String, isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        return Pager(PagingConfig(pageSize = 20)) {
            BankEduInsPvsErQuesPagingSource(
                categoryCode = categoryCode,
                year = year,
                repository = this,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getPrevYerQuesByCategoryYearList(
        categoryCode: String, year: Int, page: Int
    ): PrevYerQusQuizDetailsResponse {
        return try {
            val response = apolloClient.query(
                PrevYerQuesByCategoryYearQuery(
                    quesYear = year, categoryCode = categoryCode, page = page
                )
            ).execute()
            val yearResponse = apolloResponseToRetrofitResponse(
                response
            )
            yearResponse.convertData()
        } catch (e: Exception) {
            apolloResponseToRetrofitResponse(
                "Internet connection error."
            ).convertData()
        }
    }

    override suspend fun getPrevYerQuesByCategoryYearListRoom(
        queryCode: String, limit: Int, offset: Int
    ): List<FormatQuestionsAsExpected> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .getPvsErQuestionAsExpectedFormat(queryCode, limit, offset)
    }

    override fun PrevYerQuesByOrganizationCode(
        stringOrganizationCode: String, categoryCode: String, isApiCall: Boolean
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PrevErQusByOrgPagingSource(
                categoryCode = categoryCode,
                stringOrganizationCode = stringOrganizationCode,
                repository = this,
                roomHelper = roomHelper,
                preferencesHelper = preferencesHelper,
                isApiCall = isApiCall
            )
        }.flow
    }

    override suspend fun getPrevYerQuesByOrganizationCodeList(
        stringOrganizationCode: String, categoryCode: String, page: Int
    ): PrevYerQusQuizDetailsResponse {
        try {
            val response = apolloClient.query(
                PrevYerQuesByOrganizationCodeQuery(
                    organizationCode = stringOrganizationCode,
                    categoryCode = categoryCode,
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


    private inline fun <reified T : Any> Response<ResponseBody>.convertJson(): T {
        val body = if (this.isSuccessful) {
            this.body()?.string()
        } else throw HttpException(this)

        return GsonBuilder().serializeNulls().create().fromJson(
            body, T::class.java
        )
    }
}