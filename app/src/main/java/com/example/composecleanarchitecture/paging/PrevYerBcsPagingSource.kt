package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.previousyear_question.IPvsYearQuesRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_LIST_DETAILS

class PrevYerBcsPagingSource(
    private val sequence: String, private val categoryCode: String, private val year: Int,
    private var repository: IPvsYearQuesRepository,
    private val roomHelper: RoomHelper,
    private val preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true,
) : PagingSource<Int, PreviousYearQuestionDetailsFormat>() {

    override fun getRefreshKey(state: PagingState<Int, PreviousYearQuestionDetailsFormat>): Int {
        return state.anchorPosition ?: 0
    }

    private val finalList: ArrayList<PreviousYearQuestionDetailsFormat> = ArrayList()
    private var startIndex = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PreviousYearQuestionDetailsFormat> {
        return try {
            val nextPage = params.key ?: 1
            val resultList: ArrayList<PreviousYearQuestionDetailsFormat> = ArrayList()
            if (isApiCall) {
                val outletResponse = repository.getPrevYerQuesBySeqCategoryYearList(
                    sequence = sequence, categoryCode = categoryCode, year = year,
                    page = nextPage
                )
                val list = outletResponse.data?.previousYearQuestions?.data
                list?.forEachIndexed { index, item ->
                    val question = item.attributes?.question?.data?.attributes
                    resultList.add(
                        PreviousYearQuestionDetailsFormat(
                            lazyId = startIndex + index,
                            question = question?.question!!,
                            answer = question?.answer!!,
                            optionA = question?.optionA!!,
                            optionB = question?.optionB!!,
                            optionC = question?.optionC!!,
                            optionD = question?.optionD!!,
                            optionE = question?.optionE!!,
                            details = question?.details!!,
                            gradeName = item.attributes?.grade?.data?.attributes?.name!!,
                            subjectName = item.attributes?.subject?.data?.attributes?.name!!,
                            designationName = item.attributes?.designation?.data?.attributes?.name!!,
                            organizationName = item.attributes?.organization?.data?.attributes?.name!!,
                            year = item.attributes?.year.toString(),
                            queryCode = "$categoryCode$sequence$year"
                        )
                    )
                }
                finalList.addAll(resultList)
                startIndex = finalList.last().lazyId + 1
                if (resultList.isNullOrEmpty()) {
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .deleteBcsAndJudgeQuestion("$categoryCode$sequence$year")
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .insertBcsAndJudgeQuestion(finalList)
                    preferencesHelper.putString(
                        "$PREVIOUS_ER_QUES_LIST_DETAILS$categoryCode$sequence$year",
                        "1"
                    )
                }
                val lastPage = outletResponse.data?.previousYearQuestions?.meta?.pagination?.total
            } else {
                resultList.clear()
                val response =
                    repository.getBcsAndJudgeQuestionListRoom(
                        "$categoryCode$sequence$year",
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