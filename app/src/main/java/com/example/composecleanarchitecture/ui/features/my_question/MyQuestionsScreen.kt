package com.example.composecleanarchitecture.ui.features.my_question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecleanarchitecture.models.dashboard.year_wise.YearWiseQuestion
import com.example.composecleanarchitecture.ui.common_components.CardWithOutImage
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.secondaryColor

@Composable
fun MyQuestionsScreen(navController: NavController) {
    val state = rememberLazyGridState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = secondaryColor)
    ) {
        ToolBarLayoutComponent(
            navController = navController,
            isBackShow = true,
            title = "বিগত সালের প্রশ্ন",
            textSize = 16.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            CustomTextComponents(
                title = "বিগত সালের প্রশ্ন",
                fontWeight = FontWeight.W400,
                textColor = blackVarColor1,
                textSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(20) { index ->
                CardWithOutImage(
                    yearWiseQuestion = YearWiseQuestion(
                        false,
                        "New",
                        "বিসিএস"
                    )
                )
            }
        }
    }
}