package com.example.composecleanarchitecture.ui.features.home.components

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
import com.example.composecleanarchitecture.models.dashboard.year_wise.YearWiseQuestion
import com.example.composecleanarchitecture.ui.common_components.CardWithOutImage
import com.example.composecleanarchitecture.utils.getYearsArray


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselPagerYearWise(onItemClicked: (String) -> Unit) {
    val yearList = getYearsArray()
    val pagerState = rememberPagerState(pageCount = {
        yearList.size
    })
    Column(Modifier.padding(start = 20.dp)) {
        HorizontalPager(
            state = pagerState, pageSize = PageSize.Fixed(135.dp)
        ) { page ->
            Box(modifier = Modifier.clickable {
                onItemClicked.invoke(yearList[page])
            }) {
                CardWithOutImage(
                    yearWiseQuestion = YearWiseQuestion(
                        false,
                        "New",
                        yearList[page]
                    )
                )
            }

        }
        Spacer(modifier = Modifier.height(11.dp))
        DotsIndicator(totalDots = 10, pagerState = pagerState)
    }
}