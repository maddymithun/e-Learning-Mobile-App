package com.example.composecleanarchitecture.view_models.previousyear_question

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.repository.previousyear_question.PvsYearQuesRepository
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.cat_wise_organisations.PvsErIBAPscOrgName
import com.example.composecleanarchitecture.models.institute_qus_year.YearsOfBankInsEduForPvsErQues
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.models.tomo_sequence.BCSTomoDataFormation
import com.example.composecleanarchitecture.utils.BCS_TOMO_LIST_DATA
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_LIST_DETAILS
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA_QUESTIONS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousYearQuestionViewModel @Inject constructor(
    private var repository: PvsYearQuesRepository,
    var preferencesHelper: SharedPreferencesHelper,
    var roomHelper: RoomHelper
) : BaseViewModel() {

    fun getCategoryTomoSequence(categoryCode: String) {
        viewModelScope.launch {
            if (preferencesHelper["$BCS_TOMO_LIST_DATA$categoryCode", ""] != "1") {
                repository.getCategoryTomoSequence(categoryCode = categoryCode).collect {
                    generateUiState(103, it)
                }
            } else {
                repository.getBcsTomoDataRoom(categoryCode).collect {
                    generateUiStateLocal(104, it)
                }
            }
        }

    }

    fun insertBcsTomo(categoryCode: String, bcsSequence: List<BCSTomoDataFormation>) {
        viewModelScope.launch {
            preferencesHelper.putString("$BCS_TOMO_LIST_DATA$categoryCode", "1")
            roomHelper.getRoomDbInstance().questionCategoryDao()
                .insertBcsTomoData(bcsSequence = bcsSequence)
        }
    }

    fun insertYearListPvsErQuesInsBankIns(
        categoryCode: String,
        bcsSequence: List<YearsOfBankInsEduForPvsErQues>
    ) {
        viewModelScope.launch {
            preferencesHelper.putString(
                "$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS$categoryCode",
                "1"
            )
            roomHelper.getRoomDbInstance().questionCategoryDao()
                .deleteYearListPvsErQuesInsBankIns(categoryCode)
            roomHelper.getRoomDbInstance().questionCategoryDao()
                .insertYearListPvsErQuesInsBankIns(bcsSequence)
        }
    }

    fun insertOrganizationOfIBAPAS(
        categoryCode: String,
        bcsSequence: List<PvsErIBAPscOrgName>
    ) {
        viewModelScope.launch {
            preferencesHelper.putString(
                "org_name$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA$categoryCode",
                "1"
            )
            roomHelper.getRoomDbInstance().questionCategoryDao()
                .deleteOrgListForIbaPscPvsEr(categoryCode)
            roomHelper.getRoomDbInstance().questionCategoryDao()
                .insertYOrgListForIbaPscPvsEr(bcsSequence)
        }
    }

    fun getPrevYerQuesBySeqCategoryYear(
        sequence: String,
        categoryCode: String,
        year: Int
    ): Flow<PagingData<PreviousYearQuestionDetailsFormat>> {
        val isApiCall: Boolean =
            preferencesHelper["$PREVIOUS_ER_QUES_LIST_DETAILS$categoryCode$sequence$year", ""] != "1"
        return repository.getPrevYerQuesBySeqCategoryYear(
            sequence = sequence,
            categoryCode = categoryCode,
            year = year,
            isApiCall = isApiCall
        )
    }


    fun getCategoryWiseExmYearListResponse(categoryCode: String) {
        viewModelScope.launch {
            if (preferencesHelper["$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS$categoryCode", ""] != "1") {
                repository.getCategoryWiseExmYearListResponse(categoryCode).collect {
                    generateUiState(106, it)
                }
            } else {
                repository.getYearListPvsErQuesInsBankIns(categoryCode).collect {
                    generateUiStateLocal(107, it)
                }
            }

        }

    }

    fun getOrgNameByCategoryId(categoryCode: String) {
        viewModelScope.launch {
            if (preferencesHelper["org_name$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA$categoryCode", ""] != "1") {
                repository.getOrgNameByCategoryId(categoryCode).collect { generateUiState(107, it) }
            } else {
                repository.getOrgListForIbaPscPvsErRoom("org_name$categoryCode").collect {
                    generateUiStateLocal(108, it)
                }
            }

        }
    }

    fun getPrevYerQuesByCategoryYear(
        year: Int,
        categoryCode: String
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        val isApiCall: Boolean =
            preferencesHelper["$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS$categoryCode$year", ""] != "1"
        return repository.getPrevYerQuesByCategoryYear(year, categoryCode, isApiCall)
    }


    fun getPrevYerQuesByOrganizationCode(
        stringOrganizationCode: String,
        categoryCode: String
    ): Flow<PagingData<FormatQuestionsAsExpected>> {
        val isApiCall: Boolean =
            preferencesHelper["psc_iba$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA_QUESTIONS$categoryCode$stringOrganizationCode", ""] != "1"
        return repository.PrevYerQuesByOrganizationCode(
            stringOrganizationCode,
            categoryCode,
            isApiCall
        )
    }

}