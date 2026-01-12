package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.topics_wise.ITopicsRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.utils.DASHBOARD_SUBJECT_CATEGORY


class SubjectListPagingDataSource(
    private var repository: ITopicsRepository,
    private val roomHelper: RoomHelper,
    private val preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true
) : PagingSource<Int, SubjectListResponseDataX>() {

    override fun getRefreshKey(state: PagingState<Int, SubjectListResponseDataX>): Int {
        return state.anchorPosition ?: 0
    }

    var lastPage: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SubjectListResponseDataX> {
        return try {
            val nextPage = params.key ?: 1
            val listOfCourse: ArrayList<SubjectListResponseDataX> = ArrayList()
            listOfCourse.clear()
            if (isApiCall) {
                val outletResponse = repository.getAllSubjectList(
                    nextPage
                )
                outletResponse.data?.subjects?.data?.let {
                    listOfCourse.addAll(it)
                }
                roomHelper.getRoomDbInstance().questionCategoryDao()
                    .insertDashSubjectListCategory(listOfCourse)
                lastPage = outletResponse.data?.subjects?.meta?.pagination?.page!!
                if (nextPage == lastPage){
                    preferencesHelper.putString(DASHBOARD_SUBJECT_CATEGORY, "1")
                }
            } else {
                listOfCourse.clear()
                val response =
                    repository.getAllSubjectListRoom(params.loadSize, (nextPage-1) * params.loadSize)
                listOfCourse.addAll(response)
            }
            val resultList =
                listOfCourse.distinctBy { items -> items.id }
            LoadResult.Page(
                data = resultList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (resultList.isEmpty()) {
                    null
                } else if (nextPage == lastPage) {
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