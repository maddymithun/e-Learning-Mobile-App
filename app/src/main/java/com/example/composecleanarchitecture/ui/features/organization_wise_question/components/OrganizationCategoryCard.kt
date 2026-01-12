package com.example.composecleanarchitecture.ui.features.organization_wise_question.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.carosoulBgColor
import com.example.composecleanarchitecture.ui.theme.robotoFont
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun OrganizationCategoryCard (
    previousYearQuestion: OrganizationCategory,
    isImageShow: Boolean = true,
    maxLine: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = 25.sp,
    height: Int = 90,
    width: Int = 110
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = carosoulBgColor),
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = if (previousYearQuestion.isTracerEnable) R.drawable.ic_trace_enable else R.drawable.ic_trace_disable),
                    contentDescription = null, modifier = Modifier.padding(start = 10.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(end = 5.dp, top = 5.dp)
                        .clip(shape = RoundedCornerShape(percent = 50))
                        .background(color = activeTextColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = previousYearQuestion.qsnStatus,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontFamily = robotoFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 7.sp,
                        color = whiteColor
                    )
                }
            }
            if (isImageShow) {
                Image(
                    painterResource(id = previousYearQuestion.imageVector),
                    contentDescription = null,
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.weight(.1f))
            CustomTextComponents(
                title = previousYearQuestion.qsnCategory,
                paddingTop = 10.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp,
                maxLines = maxLine,
                lineHeight = lineHeight
            )
            Spacer(modifier = Modifier.weight(.1f))
        }
    }
}