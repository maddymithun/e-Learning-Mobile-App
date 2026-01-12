package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.organization_wise_question.IOrgWiseDesignationRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.models.designations.OrgWiseDesignationListResponseDataX
import com.example.composecleanarchitecture.utils.DESIGNATION_OF_ORGANIZATION

class OrgWiseDesigPagingDataSource(
    private val categoryCode: String,
    private var repository: IOrgWiseDesignationRepository,
    private val roomHelper: RoomHelper,
    private val preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true
) : PagingSource<Int, DesignationDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, DesignationDataModel>): Int {
        return state.anchorPosition ?: 0
    }

    private val listOfDesg: ArrayList<DesignationDataModel> = ArrayList()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DesignationDataModel> {
        return try {
            val nextPage = params.key ?: 1

            val resultList: ArrayList<DesignationDataModel> = ArrayList()
            if (isApiCall) {
                val outletResponse = repository.gerOrgWiseDesignationsList(
                    categoryCode = categoryCode,
                    page = nextPage
                )
                val temporary: ArrayList<OrgWiseDesignationListResponseDataX> = ArrayList()
                outletResponse.data?.previousYearQuestions?.data?.let { temporary.addAll(it) }
                val sortedList =
                    temporary.distinctBy { items -> items.attributes?.designation?.data?.id?.toInt() }
                sortedList.forEach { item ->
                    val data = item.attributes?.designation?.data?.attributes
                    resultList.add(
                        DesignationDataModel(
                            code = data?.code!!,
                            name = data?.name!!,
                            categoryCode = categoryCode
                        )
                    )
                }
                listOfDesg.addAll(resultList)
                if (resultList.isNullOrEmpty()) {
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .insertDesignationOfOrg(listOfDesg)
                    preferencesHelper.putString(
                        "$DESIGNATION_OF_ORGANIZATION$categoryCode",
                        "1"
                    )
                }
                //     val lastPage = outletResponse.data?.previousYearQuestions?.meta?.pagination?.total
            } else {
                resultList.clear()
                val response =
                    repository.gerOrgWiseDesignationsListRoom(
                        queryCode = categoryCode,
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