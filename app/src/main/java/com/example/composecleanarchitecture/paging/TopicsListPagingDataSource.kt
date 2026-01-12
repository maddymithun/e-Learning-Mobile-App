package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.topics_wise.ITopicsRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.utils.TOPICS_OF_SUBJECT_CATEGORY

class TopicsListPagingDataSource(
    private var repository: ITopicsRepository,
    private val roomHelper: RoomHelper,
    private val subjectCode: String = "0",
    private val preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true
) : PagingSource<Int, TopicsListResponseDataX>() {

    override fun getRefreshKey(state: PagingState<Int, TopicsListResponseDataX>): Int {
        return state.anchorPosition ?: 0
    }

    var lastPage: Int = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicsListResponseDataX> {
        return try {
            val nextPage = params.key ?: 1

            val listOfCourse: ArrayList<TopicsListResponseDataX> = ArrayList()
            val topicsList: ArrayList<TopicsListResponseDataX> = ArrayList()
            if (isApiCall) {
                listOfCourse.clear()
                val outletResponse = repository.getSubjectWiseTopicsList(
                    subjectCode,
                    nextPage
                )
                outletResponse.data?.topics?.data?.let {
                    listOfCourse.addAll(it)
                }
                listOfCourse.forEach { item ->
                    topicsList.add(
                        TopicsListResponseDataX(
                            subjectCode = subjectCode,
                            attributes = item.attributes,
                            id = item.id
                        )
                    )
                }
                roomHelper.getRoomDbInstance().questionCategoryDao()
                    .insertTopicsOfSubject(topicsList)
                lastPage = outletResponse.data?.topics?.meta?.pagination?.pageCount!!
                if (nextPage == lastPage) {
                    preferencesHelper.putString("$TOPICS_OF_SUBJECT_CATEGORY$subjectCode", "1")
                }
            } else {
                listOfCourse.clear()
                val response =
                    repository.getSubjectWiseTopicsListRoom(
                        subjectCode,
                        params.loadSize,
                        (nextPage - 1) * params.loadSize
                    )
                listOfCourse.addAll(response)
            }

            val resultList =
                listOfCourse.distinctBy { items -> items.id }
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