package com.example.composecleanarchitecture.ui.features.course

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.common_components.paginationListHandler
import com.example.composecleanarchitecture.ui.features.course.components.CourseExamDetailsComponents
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.SharedViewModel
import com.example.composecleanarchitecture.view_models.course.CourseViewModel

@Composable
fun CourseExamScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val courseCode: String = sharedViewModel._courseOutLateData.value.attributes?.code.toString()
    val syllabusCode: String = sharedViewModel.syllabusCode.value.toString()

    val courseViewModel: CourseViewModel = hiltViewModel()
    val lazyState = rememberLazyListState()
    val courseDataItemList: LazyPagingItems<CourseQuestionListResponseDataX> =
        courseViewModel.getAllCourseList(syllabusCode.toInt())
            .collectAsLazyPagingItems()
    val context = LocalContext.current

    BaseComponent(
        backgroundColor = secondaryColor,
        progressBarState = courseViewModel.showProgressBar.collectAsState(),
        unauthorizedState = courseViewModel.unauthorized.collectAsState(),
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = lazyState
            ) {
                items(
                    count = courseDataItemList.itemCount,
                    key = courseDataItemList.itemKey { item -> item.id!!.toInt() },
                    contentType = courseDataItemList.itemContentType { "contentType" },
                ) { index ->
                    val item = courseDataItemList[index]
                    item?.let {
                        CourseExamDetailsComponents(
                            index = index,
                            courseCode = courseCode,
                            it
                        ) { _, _ ->

                        }
                    }

                }
                courseDataItemList.apply {
                    this.paginationListHandler(
                        context = context, listScope = this@LazyColumn
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(start = 20.dp, end = 20.dp),
                shape = RectangleShape
            ) {
                Text(text = "Submit Answer")
            }

        }
    }


}