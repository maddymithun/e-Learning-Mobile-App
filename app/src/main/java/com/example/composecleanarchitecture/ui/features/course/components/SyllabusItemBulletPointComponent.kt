package com.example.composecleanarchitecture.ui.features.course.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.DuskBlue
import com.example.composecleanarchitecture.ui.theme.redTextColor

@Composable
fun SyllabusItemBulletPointComponent(syllabusTopics: String) {
    Row(
        modifier = Modifier.padding(top = 5.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp, top = 5.dp)
                .size(8.dp)
                .background(color = redTextColor, shape = CircleShape),
        )

        CustomTextComponents(
            title = syllabusTopics,
            textAlign= TextAlign.Start,
           textSize = 14.sp, textColor = DuskBlue
        )
    }
}