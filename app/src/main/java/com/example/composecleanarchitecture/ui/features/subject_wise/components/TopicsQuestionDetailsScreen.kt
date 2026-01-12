package com.example.composecleanarchitecture.ui.features.subject_wise.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.base.UIState
import com.example.composecleanarchitecture.models.AnswerTrackingState
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsDataX
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.BcsQuestionDetailsComponent
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.SharedViewModel

@Composable
fun TopicsQuestionDetailsScreen(
    loginViewModel: SharedViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val questionDatItem = remember {
        mutableStateListOf<PrevYerBCSQusQuizDetailsDataX>()
    }
    val questionTracking: MutableMap<Int, AnswerTrackingState> = remember { mutableMapOf() }
    val lazyState = rememberLazyListState()
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    var stateId: Int? by remember { mutableStateOf(null) }
    LaunchedEffect(key1 = true) {
        loginViewModel.getQuestionData(context)
        loginViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    stateId = it.stateId
                    isShowProgressBar.value = true
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        2024 -> {
                            val data = it.data as List<PrevYerBCSQusQuizDetailsDataX>
                            questionDatItem.addAll(data)
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

    BaseComponent(backgroundColor = MaterialTheme.colorScheme.background,
        progressBarState = loginViewModel.showProgressBar.collectAsState(),
        unauthorizedState = loginViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = isShowProgressBar.value, color = Color.Green)
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
                title = "সকল প্রশ্ন",
                textSize = 16.sp
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = lazyState
            ) {
                items(
                    count = questionDatItem.size,
                ) { index ->
                    val item = questionDatItem[index]
                    val selectedPosition = questionTracking[index]
                    item.attributes?.let {
                        BcsQuestionDetailsComponent(quesIndex = index, attributes = it,
                            selectedPosition
                        ) { _, _, itemPos ->
                            questionTracking[index] = itemPos
                        }
                    }
                }
            }
        }
    }
}