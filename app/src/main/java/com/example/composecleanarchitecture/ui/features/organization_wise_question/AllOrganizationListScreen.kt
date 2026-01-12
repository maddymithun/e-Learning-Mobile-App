package com.example.composecleanarchitecture.ui.features.organization_wise_question

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.base.UIState
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.dashboard.organization_wise.DashOrgWiseCategoryResponse
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.organization_wise_question.components.OrganizationCategoryCard
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.DESIGNATION_OF_ORGANIZATION
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.dashBoard.DashBoardViewModel


@Composable
fun AllOrganizationListScreen(navController: NavController) {
    val state = rememberLazyGridState()
    val loginViewModel: DashBoardViewModel = hiltViewModel()
    val organizeWiseCategory = remember { mutableStateListOf<OrganizationCategory>() }
    val context = LocalContext.current
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    var stateId: Int? by remember { mutableStateOf(null) }
    val preferencesHelper = SharedPreferencesHelper(context)

    LaunchedEffect(key1 = true) {
        loginViewModel.getDashBoardOrganizationQuestion()
        loginViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    stateId = it.stateId
                    isShowProgressBar.value = true
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        102 -> {
                            val data = it.data as DashOrgWiseCategoryResponse
                            val categoriesList =
                                data.data?.organizations?.data
                            categoriesList?.forEachIndexed { _, item ->
                                item.attributes?.let { items ->
                                    organizeWiseCategory.add(
                                        OrganizationCategory(
                                            isTracerEnable = false,
                                            qsnCategory = items.name!!,
                                            imageVector = R.drawable.ic_bank,
                                            qsnStatus = "New",
                                            qsnId = items.code!!
                                        )
                                    )
                                }
                            }
                        }

                        104 -> {
                            val data = it.data as List<OrganizationCategory>
                            organizeWiseCategory.clear()
                            organizeWiseCategory.addAll(data)

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
        progressBarState = loginViewModel.showProgressBar.collectAsState(),
        unauthorizedState = loginViewModel.unauthorized.collectAsState(),
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
                title = "বিগত সালের প্রশ্ন",
                textSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                CustomTextComponents(
                    title = "বিগত সালের প্রশ্ন",
                    fontWeight = FontWeight.W400,
                    textColor = blackVarColor1,
                    textSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))


            LazyVerticalGrid(
                state = state,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(organizeWiseCategory.size) { index ->
                    Box(modifier = Modifier.clickable {
                        val item = organizeWiseCategory[index]
                        if ((preferencesHelper["$DESIGNATION_OF_ORGANIZATION${item.qsnId.trim()}", ""] == "1"
                                    && !context.isInternetAvailable()) or context.isInternetAvailable()
                        ) {
                            navController.navigate("OrgWiseDesignationListScreen/${item.qsnId.trim()}")
                        } else {
                            context.showToast("Check your internet connection.")
                        }
                    }) {
                        OrganizationCategoryCard(
                            previousYearQuestion = organizeWiseCategory[index],
                            isImageShow = false,
                            lineHeight = 14.sp,
                            maxLine = 5,
                            height = 120,
                            width = 130
                        )
                    }
                }
            }
        }
    }
}