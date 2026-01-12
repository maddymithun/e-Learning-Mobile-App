package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.primaryColor
import com.example.composecleanarchitecture.ui.theme.robotoFont

@Composable
fun PageRouteComponent(tagName: String) {
    Surface(
        shape = AbsoluteCutCornerShape(topRightPercent = 50, bottomRightPercent = 50),
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier
            .background(primaryColor)
            .padding(end = 10.dp, start = 15.dp)) {
            Text(
                text = tagName,
                color = blackVarColor1,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                fontFamily = robotoFont,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 2.dp, bottom = 2.dp, start = 5.dp, end = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun prev23() {
    PageRouteComponent("zahid")
}