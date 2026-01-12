package com.example.composecleanarchitecture.ui.features.course

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecleanarchitecture.navigation_controller.Destinations
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.course.components.CourseItemComponent
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.view_models.SharedViewModel

@Composable
fun CourseOutletScreen(
    sharedViewModel: SharedViewModel, navController: NavController
) {
    val state = rememberLazyListState()
    val courseContent = remember {
        sharedViewModel._courseOutLateData.value.attributes?.courseExams?.data
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = secondaryColor)
    ) {
        ToolBarLayoutComponent(
            navController = navController,
            isBackShow = true,
            title = "চলমান  কোর্স",
            textSize = 16.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            CustomTextComponents(
                title = "চলমান  কোর্স লিস্ট",
                fontWeight = FontWeight.W400,
                textColor = blackVarColor1,
                textSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(state = state) {
            courseContent?.let {
                items(it.size) { itemIndex ->
                    CourseItemComponent(
                        (itemIndex + 1).toString(), courseContent[itemIndex]
                    ) { courseCode ->
                        sharedViewModel.syllabusCode.value = courseCode.toString()
                        navController.navigate(Destinations.CourseExamScreen.route)
                    }
                }
            }

        }
    }
}

