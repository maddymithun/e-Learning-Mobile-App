package com.example.composecleanarchitecture.ui.features.subject_wise

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.navigation_controller.Destinations
import com.example.composecleanarchitecture.ui.common_components.CardQusFromToSplitTitleComponents
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.common_components.paginationListHandlerForGrid
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.topics.TopicsViewModel
import com.google.gson.Gson

@Composable
fun TopicsWiseQuestionListScreen(
    subtopicsCode: String,
    queryCode: String,
    navController: NavController
) {

    val context = LocalContext.current

    val previousErQusViewModel: TopicsViewModel = hiltViewModel()
    val gridState = rememberLazyGridState()
    val questionDatItem: LazyPagingItems<FormatQuestionsAsExpected> =
        previousErQusViewModel.getPrevYerQuesBySubjectSubTopics(
            subtopicsCode = subtopicsCode,
            queryCode = queryCode
        ).collectAsLazyPagingItems()


    BaseComponent(
        backgroundColor = secondaryColor,
        progressBarState = previousErQusViewModel.showProgressBar.collectAsState(),
        unauthorizedState = previousErQusViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = it, color = Color.Green)
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
                items(
                    count = questionDatItem.itemCount,
                    key = questionDatItem.itemKey { item -> item.splitTo },
                    contentType = questionDatItem.itemContentType { it.splitTo }
                ) { index ->
                    val item = questionDatItem[index]
                    CardQusFromToSplitTitleComponents(
                        item?.splitFrom.toString(),
                        item?.splitTo.toString(),
                        index
                    ) {
                        val preferencesHelper = SharedPreferencesHelper(context)
                        val gson = Gson().toJson(item)
                        preferencesHelper.putString("QUESTION_DATA", gson)
                        navController.navigate(
                            Destinations.TopicsQuestionDetailsScreen.route
                        )
                    }
                }
                questionDatItem.apply {
                    this.paginationListHandlerForGrid(
                        context = context, listScope = this@LazyVerticalGrid
                    )
                }
            }

        }
    }
}