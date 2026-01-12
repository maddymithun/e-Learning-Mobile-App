package com.example.composecleanarchitecture.ui.features.year_wise_question

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.dashboard.year_wise.YearWiseQuestion
import com.example.composecleanarchitecture.ui.common_components.CardWithOutImage
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_USING_YEAR
import com.example.composecleanarchitecture.utils.getYearsArray
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.dashBoard.DashBoardViewModel

@Composable
fun AllYearCardListScreen(navController: NavController) {
    val loginViewModel: DashBoardViewModel = hiltViewModel()
    val state = rememberLazyGridState()
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val preferencesHelper = SharedPreferencesHelper(context)
    val yearList = getYearsArray()
    BaseComponent(backgroundColor = MaterialTheme.colorScheme.background,
        progressBarState = loginViewModel.showProgressBar.collectAsState(),
        unauthorizedState = loginViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = false, color = Color.Green)
        },
        unAuthorizedContent = {

        }) {
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
                items(yearList.size) { index ->
                    Box(modifier = Modifier.clickable {
                        val item = yearList[index]
                        if ((preferencesHelper["$PREVIOUS_ER_QUES_USING_YEAR$item", ""] == "1" && !context.isInternetAvailable()) || context.isInternetAvailable()) {
                            navController.navigate("PrevErQuesByYearSplitItemScreen/$item")
                        } else {
                            context.showToast("Check your internet connection.")
                        }
                    }) {
                        CardWithOutImage(
                            yearWiseQuestion = YearWiseQuestion(
                                false, "New", yearList[index]
                            )
                        )
                    }
                }
            }
        }
    }
}