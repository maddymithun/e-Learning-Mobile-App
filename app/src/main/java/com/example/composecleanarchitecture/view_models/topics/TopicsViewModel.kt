package com.example.composecleanarchitecture.view_models.topics

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.topics_wise.TopicsRepository
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.utils.DASHBOARD_SUBJECT_CATEGORY
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_USING_QUERY_CODE
import com.example.composecleanarchitecture.utils.SUBTOPICS_OF_TOPICS_SUBJECT
import com.example.composecleanarchitecture.utils.TOPICS_OF_SUBJECT_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private var repository: TopicsRepository,
    var preferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {


    fun getSubjectWiseTopics(subjectCode: String): Flow<PagingData<TopicsListResponseDataX>> {
        val isApiCall = preferencesHelper["$TOPICS_OF_SUBJECT_CATEGORY$subjectCode", ""] != "1"
        return repository.getSubjectWiseTopics(subjectCode, isApiCall)
    }


    fun getAllSubTopics(
        topicsCode: String,
        subjectCode: String
    ): Flow<PagingData<SubTopicsListResponseDataX>> {
        val isApiCall: Boolean =
            preferencesHelper["$SUBTOPICS_OF_TOPICS_SUBJECT$subjectCode$topicsCode", ""] != "1"
        return repository.getSubTopics(
            topicsCode = topicsCode,
            subjectCode = subjectCode,
            isApiCall = isApiCall
        )
    }


    fun getALlSubjects(): Flow<PagingData<SubjectListResponseDataX>> {
        val isApiCall = preferencesHelper[DASHBOARD_SUBJECT_CATEGORY, ""] != "1"
        return repository.getALlSubjects("subject_wise", isApiCall)
    }

    fun getPrevYerQuesBySubjectSubTopics(
        subtopicsCode: String,
        queryCode: String
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        val isApiCall: Boolean =
            preferencesHelper["$PREVIOUS_ER_QUES_USING_QUERY_CODE$queryCode", ""] != "1"
        return repository.getSubTopicsPvsErQues(
            subtopicsCode = subtopicsCode,
            queryCode = queryCode,
            isApiCall = isApiCall
        )
    }
}