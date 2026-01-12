package com.example.composecleanarchitecture.view_models.year_wise_question

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.year_wise_question.YearWiseQuestionRepository
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_USING_YEAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class YearWiseQuestionViewModel @Inject constructor(
    private var repository: YearWiseQuestionRepository,
    var preferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {

    fun getPreviousYearQuestionsByYear(year: String): Flow<PagingData<FormatQuestionsAsExpected>> {
        val isApiCall = preferencesHelper["$PREVIOUS_ER_QUES_USING_YEAR$year", ""] != "1"
        return repository.getPrevErQuesByYear(year = year.toInt(), isApiCall = isApiCall)
    }

}