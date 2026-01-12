package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.theme.SlateGrey
import com.example.composecleanarchitecture.ui.theme.primaryColor
import com.example.composecleanarchitecture.ui.theme.textT6HeaderColor

@Composable
fun QuestionDetailsExplanationComponents(textExplanation: String) {
    val isShowMore = remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Answer Explanation", fontSize = 16.sp, color = SlateGrey)
            Text(
                text = if (isShowMore.value) "Show Less" else "Show More",
                fontSize = 12.sp,
                color = primaryColor,
                modifier = Modifier.clickable {
                    isShowMore.value = !isShowMore.value
                })
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (isShowMore.value) {
            Text(
                text = textExplanation,
                fontSize = 14.sp,
                color = textT6HeaderColor,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
        }
    }
}