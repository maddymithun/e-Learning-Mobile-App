package com.example.composecleanarchitecture.view_models.course

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.courses.CourseRepository
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CourseViewModel @Inject constructor(
    private var repository: CourseRepository,
    var preferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {

    fun getAllCourseList(courseCOde: Int): Flow<PagingData<CourseQuestionListResponseDataX>> =
        repository.getCourseQuestions(courseCOde)

}