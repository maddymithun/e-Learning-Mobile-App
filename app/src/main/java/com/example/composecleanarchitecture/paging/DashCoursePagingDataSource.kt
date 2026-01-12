package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.courses.ICourseRepository
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX

class DashCoursePagingDataSource(
    private var repository: ICourseRepository,
) : PagingSource<Int, CourseListWithTopiscDataX>() {

    override fun getRefreshKey(state: PagingState<Int, CourseListWithTopiscDataX>): Int {
        return state.anchorPosition ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseListWithTopiscDataX> {
        return try {
            val nextPage = params.key ?: 1
            val outletResponse = repository.getAllCourseList(
                page = nextPage
            )

            val listOfCourse: ArrayList<CourseListWithTopiscDataX> = ArrayList()
            listOfCourse.clear()
            outletResponse.data?.courses?.data?.let { listOfCourse.addAll(it) }
            val resultList =
                listOfCourse.distinctBy { items -> items.attributes?.code }

            val lastPage = outletResponse.data?.courses?.meta?.pagination?.total
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