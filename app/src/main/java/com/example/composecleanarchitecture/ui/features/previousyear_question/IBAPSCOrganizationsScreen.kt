package com.example.composecleanarchitecture.ui.features.previousyear_question

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
import com.example.composecleanarchitecture.models.cat_wise_organisations.CatWiseOrganiseNameDataXX
import com.example.composecleanarchitecture.models.cat_wise_organisations.CatWiseOrganiseNameResponse
import com.example.composecleanarchitecture.models.cat_wise_organisations.PvsErIBAPscOrgName
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.CardOrganizeNameComponents
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA_QUESTIONS
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.previousyear_question.PreviousYearQuestionViewModel

@Composable
fun IBAPSCOrganizationsScreen(
    categoryCode: String, navController: NavController
) {
    val previousErQusViewModel: PreviousYearQuestionViewModel = hiltViewModel()
    val scrollState = rememberLazyGridState()
    var stateId: Int? by remember { mutableStateOf(null) }
    val sequenceSortValue = remember { mutableStateListOf<PvsErIBAPscOrgName>() }
    val context = LocalContext.current
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    val preferencesHelper = SharedPreferencesHelper(context)

    LaunchedEffect(key1 = true) {
        previousErQusViewModel.getOrgNameByCategoryId(categoryCode = categoryCode)
        previousErQusViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    stateId = it.stateId
                    isShowProgressBar.value = true
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        107 -> {
                            val data = it.data as CatWiseOrganiseNameResponse
                            val categoryList = ArrayList<CatWiseOrganiseNameDataXX>()
                            data.data?.categories?.data?.get(0)?.attributes?.catWiseOrganiseNameOrganizations?.data.let { ite ->
                                ite?.let { it1 -> categoryList.addAll(it1) }
                            }
                            categoryList.forEach { value ->
                                sequenceSortValue.add(
                                    PvsErIBAPscOrgName(
                                        code = value.attributes?.code!!,
                                        name = value.attributes?.name!!,
                                        queryCode = "org_name$categoryCode"
                                    )
                                )
                            }
                            if (preferencesHelper["org_name$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA$categoryCode", ""] != "1") {
                                previousErQusViewModel.insertOrganizationOfIBAPAS(
                                    categoryCode,
                                    sequenceSortValue
                                )
                            }
                        }

                        108 -> {
                            val data = it.data as List<PvsErIBAPscOrgName>
                            sequenceSortValue.clear()
                            sequenceSortValue.addAll(data)
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

    BaseComponent(backgroundColor = secondaryColor,
        progressBarState = previousErQusViewModel.showProgressBar.collectAsState(),
        unauthorizedState = previousErQusViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = isShowProgressBar.value, color = Color.Green)
        },
        unAuthorizedContent = {}) {
        Column {
            ToolBarLayoutComponent(
                navController = navController,
                isBackShow = true,
                title = "সকল প্রশ্ন",
                textSize = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    count = sequenceSortValue.size,
                ) { index ->
                    val item = sequenceSortValue[index]
                    CardOrganizeNameComponents(item) { onitem ->
                        if ((preferencesHelper["psc_iba$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA_QUESTIONS$categoryCode${onitem.code}", ""] == "1"
                                    && !context.isInternetAvailable()) or context.isInternetAvailable()
                        ) {
                            navController.navigate("OrgWiseIbaPasQuestionSplitItemScreen/$categoryCode/${onitem.code}")
                        }else{
                            context.showToast("Check your internet connection.")
                        }
                    }
                }
            }

        }
    }
}