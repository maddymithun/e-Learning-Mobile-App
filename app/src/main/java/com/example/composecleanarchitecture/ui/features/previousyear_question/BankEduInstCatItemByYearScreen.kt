package com.example.composecleanarchitecture.ui.features.previousyear_question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.base.UIState
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.institute_qus_year.CategoryWiseExmYearListResponse
import com.example.composecleanarchitecture.models.institute_qus_year.YearsOfBankInsEduForPvsErQues
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.InstQuesErItemCardComponents
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.previousyear_question.PreviousYearQuestionViewModel

@Composable
fun BankEduInstCatItemByYear(
    categoryCode: String,
    navController: NavController
) {

    val categoryWiseExmYearList = remember { mutableStateListOf<YearsOfBankInsEduForPvsErQues>() }
    val context = LocalContext.current
    val previousErQusViewModel: PreviousYearQuestionViewModel = hiltViewModel()
    var stateId: Int? by remember { mutableStateOf(null) }
    val gridState = rememberLazyGridState()
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    val preferencesHelper = SharedPreferencesHelper(context)
    LaunchedEffect(key1 = true) {
        previousErQusViewModel.getCategoryWiseExmYearListResponse(categoryCode)
        previousErQusViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    isShowProgressBar.value = true
                    stateId = it.stateId
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        106 -> {
                            val data = it.data as CategoryWiseExmYearListResponse
                            val categoryYearList =
                                data?.data?.categories?.data?.get(0)?.attributes?.previousYearQuestions?.data
                            val yearDistinctVal =
                                categoryYearList?.distinctBy { year -> year.attributes?.year }
                                    ?.sortedBy { years -> years.attributes?.year }
                            yearDistinctVal?.forEach { item ->
                                val data = item.attributes?.year
                                categoryWiseExmYearList.add(
                                    YearsOfBankInsEduForPvsErQues(
                                        year = data!!,
                                        queryCode = categoryCode
                                    )
                                )
                            }
                            if (preferencesHelper["$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS$categoryCode", ""] != "1") {
                                previousErQusViewModel.insertYearListPvsErQuesInsBankIns(
                                    categoryCode,
                                    categoryWiseExmYearList
                                )
                            }
                        }

                        107 -> {
                            val data = it.data as List<YearsOfBankInsEduForPvsErQues>
                            categoryWiseExmYearList.clear()
                            categoryWiseExmYearList.addAll(data)
                        }
                    }
                }

                is UIState.Error -> {
                    isShowProgressBar.value = false
                    context.showToast(it.message)
                }

                else -> {

                }
            }
        }
    }
    BaseComponent(
        backgroundColor = MaterialTheme.colorScheme.background,
        progressBarState = previousErQusViewModel.showProgressBar.collectAsState(),
        unauthorizedState = previousErQusViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = isShowProgressBar.value, color = Color.Green)
        },
        unAuthorizedContent = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = secondaryColor)
        ) {
            ToolBarLayoutComponent(
                navController = navController,
                isBackShow = true,
                title = "রাফখাতা",
                textSize = 18.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categoryWiseExmYearList.size) { index ->
                    InstQuesErItemCardComponents(
                        categoryWiseExmYearList[index]
                    ) { year ->
                        if ((preferencesHelper["$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS_QUESTIONS$categoryCode$year", ""] == "1"
                                    && !context.isInternetAvailable()) or context.isInternetAvailable()
                        ) {
                            navController.navigate("BankEduInstYearWiseQuesSplitItemScreen/$categoryCode/$year")
                        } else {
                            context.showToast("Check your internet connection.")
                        }
                    }
                }
            }
        }
    }
}