package com.example.composecleanarchitecture.view_models.organization_wise_question

import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.organization_wise_question.OrgWiseDesignationRepository
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.utils.DESIGNATION_OF_ORGANIZATION
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_USING_QUERY_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class OrgWiseDesgQuestionViewModel @Inject constructor(
    private var repository: OrgWiseDesignationRepository,
    var preferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {
    fun gerOrgWiseDesignations(
        categoryCode: String,
    ): Flow<PagingData<DesignationDataModel>> {
        val isApiCall: Boolean =
            preferencesHelper["$DESIGNATION_OF_ORGANIZATION$categoryCode", ""] != "1"
        return repository.gerOrgWiseDesignations(
            categoryCode = categoryCode,
            isApiCall = isApiCall
        )
    }


    fun prevYerQuesByOrgDesgnCode(
        organizationCode: String,
        desigCode: String
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        val isApiCall: Boolean =
            preferencesHelper["$PREVIOUS_ER_QUES_USING_QUERY_CODE$organizationCode$desigCode", ""] != "1"
        return repository.prevYerQuesByOrgDesgnCode(
            organizationCode = organizationCode,
            desigCode = desigCode,
            isApiCall = isApiCall
        )
    }

}