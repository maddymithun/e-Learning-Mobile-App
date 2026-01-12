package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.topics_wise.ITopicsRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.example.composecleanarchitecture.utils.SUBTOPICS_OF_TOPICS_SUBJECT

class SubTopicsListPagingDataSource(
    private var repository: ITopicsRepository,
    private val topicsCode: String,
    private val subjectCode: String,
    private var roomHelper: RoomHelper,
    private var preferencesHelper: SharedPreferencesHelper,
    private val isApiCall: Boolean = true
) : PagingSource<Int, SubTopicsListResponseDataX>() {

    override fun getRefreshKey(state: PagingState<Int, SubTopicsListResponseDataX>): Int {
        return state.anchorPosition ?: 0
    }

    var lastPage: Int = 0
    val roomList: ArrayList<SubTopicsListResponseDataX> = ArrayList()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SubTopicsListResponseDataX> {
        return try {
            val nextPage = params.key ?: 1
            val listOfCourse: ArrayList<SubTopicsListResponseDataX> = ArrayList()
            val subtopics: ArrayList<SubTopicsListResponseDataX> = ArrayList()
            if (isApiCall) {
                listOfCourse.clear()
                val outletResponse = repository.getSubTopicsList(
                    topicsCode,
                    nextPage
                )
                outletResponse.data?.subTopics?.data?.let {
                    listOfCourse.addAll(it)
                }
                listOfCourse.forEach { item ->
                    subtopics.add(
                        SubTopicsListResponseDataX(
                            subjectCode = subjectCode,
                            topicsCode = topicsCode,
                            attributes = item.attributes,
                            id = item.id
                        )
                    )
                }
                roomList.addAll(subtopics)
                lastPage = outletResponse.data?.subTopics?.meta?.pagination?.total!!
                if (subtopics.isNullOrEmpty()) {
                    roomHelper.getRoomDbInstance().questionCategoryDao()
                        .insertSubtopicsOfTopicsAndSubject(roomList)
                    preferencesHelper.putString(
                        "$SUBTOPICS_OF_TOPICS_SUBJECT$subjectCode$topicsCode",
                        "1"
                    )
                }
            } else {
                listOfCourse.clear()
                val response =
                    repository.getSubtopicsOfTopicsOfSubjectRoom(
                        subjectCode,
                        topicsCode,
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
                }  else {
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