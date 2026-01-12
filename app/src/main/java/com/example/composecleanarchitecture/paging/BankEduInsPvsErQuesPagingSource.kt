package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.previousyear_question.IPvsYearQuesRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsDataX
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS
import com.google.gson.Gson

class BankEduInsPvsErQuesPagingSource(
    private val categoryCode: String, private val year: Int,
    private var repository: IPvsYearQuesRepository,
    private val roomHelper: RoomHelper,
    private val preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true,
) : PagingSource<Int, FormatQuestionsAsExpected>() {

    override fun getRefreshKey(state: PagingState<Int, FormatQuestionsAsExpected>): Int {
        return state.anchorPosition ?: 0
    }

    private var startIndex = 0
    private var lastPage = 0
    private val myList = ArrayList<PrevYerBCSQusQuizDetailsDataX>()
    private val finalList: ArrayList<FormatQuestionsAsExpected> = ArrayList()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FormatQuestionsAsExpected> {
        return try {
            val nextPage = params.key ?: 1



            /* list?.let { aList ->
                 for (i in aList.indices step 100) {
                     val splitTO = minOf(i + 100, aList.size)
                     val chunk = aList.subList(i, splitTO)
                     resultList.add(
                         FormatQusAsYearCardModel(
                             data = chunk,
                             year = year,
                             splitFrom = i + 1,
                             splitTo = splitTO
                         )
                     )
                 }
             }*/
            val resultList: ArrayList<FormatQuestionsAsExpected> = ArrayList()
            if (isApiCall) {
                val outletResponse = repository.getPrevYerQuesByCategoryYearList(
                    categoryCode = categoryCode, year = year,
                    page = nextPage
                )
                val list = outletResponse.data?.previousYearQuestions?.data
                list?.let { aList ->
                    myList.addAll(aList)
                    for (i in startIndex..<myList.size step 100) {
                        val splitTO = minOf(i + 100, myList.size)
                        val chunk = myList.subList(i, splitTO)
                        resultList.add(
                            FormatQuestionsAsExpected(
                                data = Gson().toJson(chunk),
                                queryCode = "bankEdu$categoryCode$year",
                                organization = "0",
                                splitFrom = i + 1,
                                splitTo = splitTO
                            )
                        )
                    }
                    startIndex = myList.size
                }
                lastPage = outletResponse.data?.previousYearQuestions?.meta?.pagination?.total!!
                finalList.addAll(resultList)

                if (resultList.isNullOrEmpty()) {
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .deletePvsErQuestionAsExpectedFormat("bankEdu$categoryCode$year")
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .insertPvsErQuestionAsExpectedFormat(finalList)
                    preferencesHelper.putString(
                        "$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS$categoryCode$year",
                        "1"
                    )
                }
            }
            else {
                resultList.clear()
                val response =
                    repository.getPrevYerQuesByCategoryYearListRoom(
                        "bankEdu$categoryCode$year",
                        params.loadSize,
                        (nextPage - 1) * params.loadSize
                    )
                resultList.addAll(response)
            }

            LoadResult.Page(
                data = resultList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (resultList.isEmpty()) {
                    null
                } else {
                    nextPage.plus(1)
                }
            )
        } catch (exception: NetworkErrorExceptions) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return LoadResult.Error(NetworkErrorExceptions(errorMessage = exception.message))
        }
    }
}