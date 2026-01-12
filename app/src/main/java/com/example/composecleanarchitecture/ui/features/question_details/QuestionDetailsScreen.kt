package com.example.composecleanarchitecture.ui.features.question_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.question_details.components.QuestionDetailsComponent
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.pathIndicatorColor
import com.example.composecleanarchitecture.ui.theme.secondaryColor

@Composable
fun QuestionDetailsScreen(navController: NavController) {
    val state = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = secondaryColor)
    ) {
        ToolBarLayoutComponent(
            navController = navController,
            isBackShow = true,
            title = "সকল প্রশ্ন",
            textSize = 16.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(color = pathIndicatorColor)
                .padding(start = 20.dp, end = 20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                CustomTextComponents(
                    title = "হোম",
                    fontWeight = FontWeight.W400,
                    textSize = 12.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    modifier = Modifier.height(36.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                CustomTextComponents(
                    title = "প্রতিষ্ঠান লিস্ট",
                    fontWeight = FontWeight.W400,
                    textSize = 12.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    modifier = Modifier.height(36.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                CustomTextComponents(
                    title = "পদবী লিস্ট",
                    fontWeight = FontWeight.W400,
                    textSize = 12.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    modifier = Modifier.height(36.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
                CustomTextComponents(
                    title = "সকল প্রশ্ন",
                    fontWeight = FontWeight.W400,
                    textSize = 12.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )

            }
        }
        LazyColumn(Modifier.fillMaxWidth(), state = state) {
            items(10) { index ->
                QuestionDetailsComponent()
            }
        }
    }
}

@Preview
@Composable
fun sdsd() {
    QuestionDetailsScreen(rememberNavController())
}