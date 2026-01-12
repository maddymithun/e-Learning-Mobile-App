package com.example.composecleanarchitecture.ui.features.subject_wise.components

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX
import com.example.composecleanarchitecture.ui.features.home.components.DotsIndicator
import com.example.composecleanarchitecture.view_models.topics.TopicsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselSubjectCategory(
    items: Int,
    onItemClicked: (String) -> Unit
) {
    val loginViewModel: TopicsViewModel = hiltViewModel()
    val courseDataItemList: LazyPagingItems<SubjectListResponseDataX> =
        loginViewModel.getALlSubjects().collectAsLazyPagingItems()

    val pagerState = rememberPagerState(pageCount = {
        courseDataItemList.itemCount
    })
    Column(Modifier.padding(start = 20.dp)) {
        HorizontalPager(
            state = pagerState, pageSize = PageSize.Fixed(150.dp)
        ) { page ->
            val item = courseDataItemList[page]
            Box(modifier = Modifier.clickable {
                onItemClicked.invoke("${item?.attributes?.code}")
            }) {
                item?.let { SubjectCategoryCardComponent(topics = it) }
            }

        }
        Spacer(modifier = Modifier.height(11.dp))
        DotsIndicator(totalDots = items, pagerState = pagerState)
    }
}