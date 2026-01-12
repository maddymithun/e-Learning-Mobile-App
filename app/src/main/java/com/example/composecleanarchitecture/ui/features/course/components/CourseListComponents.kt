package com.example.composecleanarchitecture.ui.features.course.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.ImportContacts
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.brandShade4TextColor
import com.example.composecleanarchitecture.ui.theme.carosoulBgColor
import com.example.composecleanarchitecture.ui.theme.robotoFont

@Composable
fun CourseListComponents(course: CourseListWithTopiscDataX) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = carosoulBgColor),
        modifier = Modifier
            .height(170.dp)
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Model Test",
                    modifier = Modifier.padding(end = 5.dp),
                    fontFamily = robotoFont,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = brandShade4TextColor
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FormatListNumbered,
                        modifier = Modifier.size(10.dp),
                        contentDescription = null
                    )
                    Text(
                        text = "${course.attributes?.code}",
                        fontFamily = robotoFont,
                        modifier = Modifier.padding(start = 5.dp),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = brandShade4TextColor
                    )

                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .border(width = 1.dp, color = Color(0xFF308F9D))
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                CustomTextComponents(
                    title = "${course.attributes?.name}",
                    textSize = 16.sp,
                    textAlign = TextAlign.Start,
                    paddingTop = 15.dp
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.ImportContacts,
                    modifier = Modifier.size(10.dp),
                    contentDescription = null
                )
                Text(
                    text = "${course.attributes?.courseExams?.data?.size}",
                    fontFamily = robotoFont,
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = brandShade4TextColor
                )

            }
        }
    }
}