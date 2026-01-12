package com.example.composecleanarchitecture.view_models.dashBoard

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.courses.CourseRepository
import com.example.composecleanarchitecture.data.repository.dashboard.DashBoardRepository
import com.example.composecleanarchitecture.data.repository.topics_wise.TopicsRepository
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.utils.DASHBOARD_ORG_CATEGORY
import com.example.composecleanarchitecture.utils.DASHBOARD_SUBJECT_CATEGORY
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private var repository: DashBoardRepository,
    var preferencesHelper: SharedPreferencesHelper,
    private val courseRepository: CourseRepository,
    private val topicsRepository: TopicsRepository
) : BaseViewModel() {

    fun getPrevErQuestCategory() {
        viewModelScope.launch {
            if (preferencesHelper[PREVIOUS_ER_CATEGORY, ""] != "1") {
                repository.getDashBoardPrevYearQuestion().collect {
                    generateUiState(101, it)
                }
            } else {
                repository.getPvsErQusCatFromRoom().collect {
                    generateUiStateLocal(103, it)
                }
            }

        }
    }

    fun savePreviousYearCategories(pvsErQusCategory: List<PreviousYearQuestion>) {
        viewModelScope.launch {
            preferencesHelper.putString(PREVIOUS_ER_CATEGORY, "1")
            repository.savePreviousYearCategories(pvsErQusCategory)
        }
    }

    fun saveDashBoardOrganizationCategories(pvsErQusCategory: List<OrganizationCategory>) {
        viewModelScope.launch {
            preferencesHelper.putString(DASHBOARD_ORG_CATEGORY, "1")
            repository.saveDashOrgCategory(pvsErQusCategory)
        }
    }

    fun getDashBoardOrganizationQuestion() {
        viewModelScope.launch {
            if (preferencesHelper[DASHBOARD_ORG_CATEGORY, ""] != "1") {
                repository.getDashBoardOrganizationQuestion().collect {
                    generateUiState(102, it)
                }
            } else {
                repository.getDashBoardOrganizationQuestionRoom().collect {
                    generateUiStateLocal(104, it)
                }
            }

        }
    }

    fun getAllCourseList(): Flow<PagingData<CourseListWithTopiscDataX>> =
        courseRepository.getALlCourse()


    fun getALlSubjects(): Flow<PagingData<SubjectListResponseDataX>> {
        val isApiCall = preferencesHelper[DASHBOARD_SUBJECT_CATEGORY, ""] != "1"
        return topicsRepository.getALlSubjects("subject_wise", isApiCall)
    }


}