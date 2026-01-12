package com.example.composecleanarchitecture.ui.features.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.navigation_controller.Destinations
import com.example.composecleanarchitecture.ui.common_components.CardCourseModelTestComponents
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.common_components.paginationListHandler
import com.example.composecleanarchitecture.ui.features.course.components.CourseListComponents
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.SharedViewModel
import com.example.composecleanarchitecture.view_models.dashBoard.DashBoardViewModel

@Composable
fun AllCoursesScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    val loginViewModel: DashBoardViewModel = hiltViewModel()

    val courseDataItemList: LazyPagingItems<CourseListWithTopiscDataX> =
        loginViewModel.getAllCourseList()
            .collectAsLazyPagingItems()

    val context = LocalContext.current

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                items(
                    count = courseDataItemList.itemCount,
                    key = courseDataItemList.itemKey { item -> item.id!!.toInt() },
                    contentType = courseDataItemList.itemContentType { "contentType" }
                ) { index ->
                    val item = courseDataItemList[index]

                    Box(modifier = Modifier.clickable {
                        if (item != null) {
                            sharedViewModel._courseOutLateData.value = item
                        }
                        navController.navigate(Destinations.CourseOutletScreen.route)
                    }) {
                        item?.let {
                            CourseListComponents(
                                it
                            )
                        }
                    }
                }
                courseDataItemList.apply {
                    this.paginationListHandler(
                        context = context, listScope = this@LazyColumn
                    )
                }
            }

        }
    }


}