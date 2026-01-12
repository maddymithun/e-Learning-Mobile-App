package com.example.composecleanarchitecture.ui.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.blackVarColor1

@Composable
fun CategoryHeadingComponent(
    categoryTitle: String, numberOfSubCategory: String, onSobguloClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextComponents(
                title = categoryTitle,
                fontWeight = FontWeight.W400,
                textSize = 16.sp,
                textColor = blackVarColor1
            )
            Row(modifier = Modifier.clickable {
                onSobguloClick.invoke()
            }) {
                CustomTextComponents(
                    title = "সবগুলো($numberOfSubCategory)  >>",
                    fontWeight = FontWeight.W500,
                    textSize = 12.sp,
                    textColor = activeTextColor
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }

}