package com.example.composecleanarchitecture.ui.features.course.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataXX
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.IcedBlue
import com.example.composecleanarchitecture.utils.convertAsExpectedDate

@Composable
fun CourseItemComponent(
    index: String = "1",
    courseData: CourseListWithTopiscDataXX,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
            .clickable {
                courseData.attributes?.code
                    ?.let { onItemClick.invoke(it) }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(IcedBlue)
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CustomTextComponents(
                    title = index,
                    textSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    thickness = 1.dp, modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(top = 20.dp, bottom = 20.dp)

                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = .77f)
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                CustomTextComponents(
                    title = "Exam Syllabus",
                    textSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
                courseData.attributes?.syllabus?.let {
                    SyllabusItemBulletPointComponent(it.trim())
                }
                Spacer(modifier = Modifier.width(5.dp))
                // SyllabusItemBulletPointComponent()
                // SyllabusItemBulletPointComponent()
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(5.dp))
                CustomTextComponents(
                    title = "Start at: \n${courseData.attributes?.examDate?.convertAsExpectedDate()}",
                    textSize = 14.sp
                )
            }
        }


    }
}


