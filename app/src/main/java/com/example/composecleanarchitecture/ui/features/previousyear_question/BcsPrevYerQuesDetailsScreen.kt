package com.example.composecleanarchitecture.ui.features.previousyear_question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composecleanarchitecture.models.AnswerTrackingState
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.common_components.paginationListHandler
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.FormatedPreviousErQuesDetails
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.previousyear_question.PreviousYearQuestionViewModel


@Composable
fun BcsPrevYerQuesDetailsScreen(
    sequence: String,
    categoryCode: String,
    year: Int,
    navController: NavController
) {

    val context = LocalContext.current
    val lazyState = rememberLazyListState()
    val questionTracking: MutableMap<Int, AnswerTrackingState> = remember { mutableMapOf() }
    val previousErQusViewModel: PreviousYearQuestionViewModel = hiltViewModel()
    val questionDatItem: LazyPagingItems<PreviousYearQuestionDetailsFormat> =
        previousErQusViewModel.getPrevYerQuesBySeqCategoryYear(
            sequence = sequence,
            categoryCode = categoryCode,
            year = year
        ).collectAsLazyPagingItems()
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
            state = lazyState) {
            items(questionDatItem.itemCount) { index ->
               // key(index) {
                    val item = questionDatItem[index]
                    val selectedPosition = questionTracking[index]

                    item?.let {
                        FormatedPreviousErQuesDetails(
                            quesIndex = index,
                            attributes = it,
                            selectedPosition
                        ) { _, _, itemPos ->
                            questionTracking[index] = itemPos
                        }
                    }
            //    }
            }
            questionDatItem.apply {
                this.paginationListHandler(
                    context = context, listScope = this@LazyColumn
                )
            }
        }

       /* LazyColumn(

            state = lazyState
        ) {
            items(
                questionDatItem,
            ) { index ->
                val item = questionDatItem[index]
                val selectedPosition = questionTracking[index]

                item?.let {
                    FormatedPreviousErQuesDetails(
                        quesIndex = index,
                        attributes = it,
                        selectedPosition
                    ) { _, _, itemPos ->
                        questionTracking[index] = itemPos
                    }
                }
            }
            questionDatItem.apply {
                this.paginationListHandler(
                    context = context, listScope = this@LazyColumn
                )
            }
        }*/
    }
}