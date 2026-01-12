package com.example.composecleanarchitecture.data.repository.dashboard

import com.apollographql.apollo3.ApolloClient
import com.example.composecleanarchitecture.DashOrganizationCategoryQuery
import com.example.composecleanarchitecture.DashPrevERQuESTCategoryQuery
import com.example.composecleanarchitecture.SequenceOfExamTomoQuery
import com.example.composecleanarchitecture.base.BaseRepository
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.data.network.apolloResponseToRetrofitResponse
import com.example.composecleanarchitecture.data.network.convertData
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.dashboard.organization_wise.DashOrgWiseCategoryResponse
import com.example.composecleanarchitecture.models.dashboard.previous_year.DashBoardPrevErQuest
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.models.tomo_sequence.DataX
import com.example.composecleanarchitecture.models.tomo_sequence.TomoBcsSequenceResponse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class DashBoardRepository @Inject constructor(
    override var apolloClient: ApolloClient,
    override var apiService: IApiService,
    override var roomHelper: RoomHelper,
    override var preferencesHelper: SharedPreferencesHelper
) : IDashBoardRepository, BaseRepository() {
    override suspend fun getDashBoardPrevYearQuestion(
    ): Flow<AppNetworkState<DashBoardPrevErQuest>> {
        return try {
            val response = apolloClient.query(
                DashPrevERQuESTCategoryQuery()
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

    override suspend fun getPvsErQusCatFromRoom(): Flow<List<PreviousYearQuestion>> {
        return roomHelper.getRoomDbInstance().questionCategoryDao().getPvsErCategory()
    }

    override suspend fun getDashBoardOrganizationQuestion(): Flow<AppNetworkState<DashOrgWiseCategoryResponse>> {
        try {
            val response = apolloClient.query(
                DashOrganizationCategoryQuery()
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

    override suspend fun getDashBoardOrganizationQuestionRoom(): Flow<List<OrganizationCategory>> {
        return roomHelper.getRoomDbInstance().questionCategoryDao().getDashOrgCategory()
    }

    override suspend fun getCategoryTomoSequence(categoryCode: String): Flow<AppNetworkState<ArrayList<DataX>>> {
        try {
            var currentIndex = 0
            var totalIndex = Int.MAX_VALUE
            val tomoSequenceList = ArrayList<DataX>()
            while (currentIndex < totalIndex) {
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

    override suspend fun savePreviousYearCategories(pvsErQusCategory: List<PreviousYearQuestion>): List<Long> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .insertPvsErCategory(pvsErQusCategory)
    }

    override suspend fun saveDashOrgCategory(pvsErQusCategory: List<OrganizationCategory>): List<Long> {
        return roomHelper.getRoomDbInstance().questionCategoryDao()
            .insertDashOrgCategory(pvsErQusCategory)
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