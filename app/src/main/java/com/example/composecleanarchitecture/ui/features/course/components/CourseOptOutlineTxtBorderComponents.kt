package com.example.composecleanarchitecture.ui.features.course.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.ui.common_components.CommonMathViewTextComponent
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.greenTextColor
import com.example.composecleanarchitecture.ui.theme.optionTextColor
import com.example.composecleanarchitecture.ui.theme.outlineBorderColor
import com.example.composecleanarchitecture.ui.theme.redTextColor
import com.example.composecleanarchitecture.ui.theme.unselectedBottomBarColor
import com.example.composecleanarchitecture.ui.theme.whiteColor


@Composable
fun CourseOptOutlineTxtBorderComponents(
    index: String,
    title: String,
    answered: Boolean = false,
    backGroundColor: Color = outlineBorderColor,
    textColor: Color = optionTextColor,
    isAnswerClicked: (index: String, isAnswered: Boolean) -> Unit
) {
    var isAnswered by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (backGroundColor) {
                greenTextColor -> {
                    Image(
                        painter = painterResource(id = R.drawable.right_answer),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )

                }
                else -> {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp, color = unselectedBottomBarColor,
                                shape = CircleShape
                            )
                            .padding(6.dp)
                            .clickable {
                                if (!isAnswered) {
                                    isAnswered = true
                                    isAnswerClicked.invoke(index, true)
                                }
                            }
                    ) {
                        CustomTextComponents(
                            title = "$index",
                            fontWeight = FontWeight.W400,
                            textSize = 16.sp,
                            textColor = textColor,
                            textAlign = TextAlign.Center,
                            paddingStart = 6.dp,
                            paddingEnd = 6.dp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            CommonMathViewTextComponent(
                title = title,
                textColor = optionTextColor ,
            )
        }
    }
}