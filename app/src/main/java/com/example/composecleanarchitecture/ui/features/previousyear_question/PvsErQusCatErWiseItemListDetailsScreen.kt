package com.example.composecleanarchitecture.ui.features.previousyear_question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecleanarchitecture.models.AnswerTrackingState
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsDataX
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.BcsQuestionDetailsComponent
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.SharedViewModel


@Composable
fun PvsErQusCatErWiseItemListDetailsScreen(
    viewModel: SharedViewModel,
    navController: NavController
) {

    val questionDatItem: List<PrevYerBCSQusQuizDetailsDataX> = remember {
        viewModel.questionList
    }
    val lazyState = rememberLazyListState()
    val questionTracking: MutableMap<Int, AnswerTrackingState> = remember { mutableMapOf() }
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
                    BcsQuestionDetailsComponent(
                        quesIndex = index, attributes = it,
                        selectedPosition
                    ) { _, _, itemPos ->
                        questionTracking[index] = itemPos
                    }
                }
            }
        }
    }
}
