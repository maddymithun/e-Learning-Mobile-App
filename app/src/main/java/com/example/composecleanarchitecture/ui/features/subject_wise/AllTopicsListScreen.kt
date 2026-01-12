package com.example.composecleanarchitecture.ui.features.subject_wise

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
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.common_components.paginationListHandlerForGrid
import com.example.composecleanarchitecture.ui.features.subject_wise.components.TopicsCardWithOutImage
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.SUBTOPICS_OF_TOPICS_SUBJECT
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.SharedViewModel
import com.example.composecleanarchitecture.view_models.topics.TopicsViewModel

@Composable
fun AllTopicsListScreen(
    subjectCode: String,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    val loginViewModel: TopicsViewModel = hiltViewModel()
    val courseDataItemList: LazyPagingItems<TopicsListResponseDataX> =
        loginViewModel.getSubjectWiseTopics(
            subjectCode = subjectCode
        ).collectAsLazyPagingItems()

    val state = rememberLazyGridState()


    val context = LocalContext.current
    val preferencesHelper = SharedPreferencesHelper(context)

    BaseComponent(
        backgroundColor = secondaryColor,
        progressBarState = loginViewModel.showProgressBar.collectAsState(),
        unauthorizedState = loginViewModel.unauthorized.collectAsState(),
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
                state = state,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    count = courseDataItemList.itemCount,
                    key = courseDataItemList.itemKey { item -> item.attributes?.code!! },
                    contentType = courseDataItemList.itemContentType { it.attributes?.code }
                ) { index ->
                    val item = courseDataItemList[index]

                    Box(modifier = Modifier.clickable {
                        item?.let {
                            val code = it.attributes?.code
                            if ((preferencesHelper["$SUBTOPICS_OF_TOPICS_SUBJECT$subjectCode$code", ""] == "1" && !context.isInternetAvailable()) or context.isInternetAvailable()) {
                                navController.navigate("AllSubtopicsListScreen/$subjectCode/$code")
                            } else {
                                context.showToast("Check your internet connection.")
                            }

                        }

                    }) {
                        item?.let {
                            TopicsCardWithOutImage(
                                it
                            )
                        }
                    }
                }
                courseDataItemList.apply {
                    this.paginationListHandlerForGrid(
                        context = context, listScope = this@LazyVerticalGrid
                    )
                }
            }
        }
    }
}