package com.example.composecleanarchitecture.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.data.repository.courses.ICourseRepository
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX

class CourseQuestionPagingDataSource(
    private var repository: ICourseRepository,
    private var courseCode: Int
) : PagingSource<Int, CourseQuestionListResponseDataX>() {

    override fun getRefreshKey(state: PagingState<Int, CourseQuestionListResponseDataX>): Int {
        return state.anchorPosition ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseQuestionListResponseDataX> {
        return try {
            val nextPage = params.key ?: 1
            val outletResponse = repository.getCourseQuestionsList(
                courseCode,
                nextPage
            )

            val listOfCourse: ArrayList<CourseQuestionListResponseDataX> = ArrayList()
            listOfCourse.clear()
            outletResponse.data?.courseExamQuestions?.data?.let {
                listOfCourse.addAll(it)
            }
            val resultList =
                listOfCourse.distinctBy { items -> items.id }

            val lastPage = outletResponse.data?.courseExamQuestions?.meta?.pagination?.total
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