package com.example.composecleanarchitecture.view_models

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.composecleanarchitecture.base.BaseViewModel
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.data.network.resolveError
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.FormatQusAsYearCardModel
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsDataX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

open class SharedViewModel : BaseViewModel() {

    var _questionList = mutableStateListOf<PrevYerBCSQusQuizDetailsDataX>()
    val questionList get() = _questionList.toList()
    fun addAllQuestion(question: FormatQusAsYearCardModel?, context: Context) {
        viewModelScope.launch {
            val preferencesHelper = SharedPreferencesHelper(context)
            val gson = Gson().toJson(question)
            preferencesHelper.putString("QUESTION_DATA", gson)
        }
    }

    fun getQuestionData(context: Context) {
        viewModelScope.launch {
            val preferencesHelper = SharedPreferencesHelper(context)
            val data = preferencesHelper["QUESTION_DATA", ""]
            val question = Gson().fromJson(data, FormatQuestionsAsExpected::class.java)
            val gson = Gson()
            val itemType = object : TypeToken<List<PrevYerBCSQusQuizDetailsDataX>>() {}.type
            val yourList: List<PrevYerBCSQusQuizDetailsDataX> =
                gson.fromJson(question.data, itemType)
            val apiResponse = callApi {
                yourList
            }
            apiResponse.collect {
                generateUiState(2024, it)
            }
        }
    }

    var _courseOutLateData = mutableStateOf(CourseListWithTopiscDataX())
    var syllabusCode = mutableStateOf("")
    var selectedCategory = mutableStateOf("")

    private suspend fun <T : Any> callApi(callback: suspend () -> T): Flow<AppNetworkState<T>> =
        flow {
            emit(AppNetworkState.Loading)
            try {
                val networkCall = AppNetworkState.Data(callback())
                emit(networkCall)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(e.resolveError())
            }
        }


}