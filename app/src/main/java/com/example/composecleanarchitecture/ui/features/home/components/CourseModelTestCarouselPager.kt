package com.example.composecleanarchitecture.ui.features.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.ui.common_components.CardCourseModelTestComponents
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.dashBoard.DashBoardViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseModelTestCarouselPager(items:Int,onItemClicked: (CourseListWithTopiscDataX) -> Unit) {
    val loginViewModel: DashBoardViewModel = hiltViewModel()

    val courseDataItemList: LazyPagingItems<CourseListWithTopiscDataX> =
        loginViewModel.getAllCourseList()
            .collectAsLazyPagingItems()
    val pagerState = rememberPagerState(pageCount = {
        courseDataItemList.itemCount
    })

    Column(Modifier.padding(start = 20.dp).background(secondaryColor)) {
        HorizontalPager(
            state = pagerState, pageSize = PageSize.Fixed(262.dp)
        ) { page ->
            Box(modifier = Modifier.clickable {
                onItemClicked.invoke(courseDataItemList[page]!!)
            }) {
                courseDataItemList[page]?.let {
                    CardCourseModelTestComponents(
                        it
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(11.dp))
        //val count = courseDataItemList.itemCount
        DotsIndicator(totalDots = items, pagerState = pagerState)
    }
}