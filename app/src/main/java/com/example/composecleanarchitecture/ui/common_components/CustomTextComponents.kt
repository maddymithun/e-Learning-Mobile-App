package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.theme.brandShade4TextColor
import com.example.composecleanarchitecture.ui.theme.robotoFont

@Composable
fun CustomTextComponents(
    title: String,
    fontFamily: FontFamily = robotoFont,
    fontWeight: FontWeight = FontWeight.W500,
    letterSpacing: TextUnit = 0.01.sp,
    textColor: Color = brandShade4TextColor,
    textSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    paddingStart: Dp = 0.dp,
    paddingEnd: Dp = 0.dp,
    paddingTop: Dp = 0.dp,
    lineHeight:TextUnit = 25.sp,
    paddingBottom: Dp = 0.dp,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = Modifier.padding(
            start = paddingStart,
            top = paddingTop,
            end = paddingEnd,
            bottom = paddingBottom
        ),
        text = title,
        fontSize = textSize,
        color = textColor,
        lineHeight=lineHeight,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        maxLines = maxLines
    )
}