package com.example.composecleanarchitecture.ui.features.question_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.greenTextColor
import com.example.composecleanarchitecture.ui.theme.optionTextColor
import com.example.composecleanarchitecture.ui.theme.outlineBorderColor
import com.example.composecleanarchitecture.ui.theme.questionDetailsTextColor
import com.example.composecleanarchitecture.ui.theme.redTextColor
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun QuestionDetailsComponent() {
    val rightAnswer = "A"
    var isAnswerGiven by remember { mutableStateOf(false) }
    var isRightA by remember { mutableStateOf(outlineBorderColor) }
    var isRightAText by remember { mutableStateOf(optionTextColor) }
    var isRightB by remember { mutableStateOf(outlineBorderColor) }
    var isRightBText by remember { mutableStateOf(optionTextColor) }
    var isRightC by remember { mutableStateOf(outlineBorderColor) }
    var isRightCText by remember { mutableStateOf(optionTextColor) }
    var isRightD by remember { mutableStateOf(outlineBorderColor) }
    var isRightDText by remember { mutableStateOf(optionTextColor) }
    var isRightE by remember { mutableStateOf(outlineBorderColor) }
    var isRightEText by remember { mutableStateOf(optionTextColor) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(color = whiteColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(fraction = .9f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomTextComponents(
                    title = "1 . ",
                    fontWeight = FontWeight.W400,
                    textSize = 14.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )
                CustomTextComponents(
                    title = "কোন বাক্যে 'ঢাক্\u200C ঢাক্\u200C গুডু গুডু' প্রবাদটির  বিশেষ অর্থ প্রকাশ পেয়েছে?",
                    fontWeight = FontWeight.W400,
                    textSize = 14.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )
            }
            Icon(
                Icons.Default.MoreHoriz,
                contentDescription = null,
                tint = activeTextColor,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 46.dp, end = 46.dp, top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                CustomTextComponents(
                    title = ". সালঃ ২০২০",
                    fontWeight = FontWeight.W400,
                    textSize = 8.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
                CustomTextComponents(
                    title = ". Ahsanullah Science & Technology University",
                    fontWeight = FontWeight.W400,
                    textSize = 8.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
            }
            Column {
                CustomTextComponents(
                    title = ". গ্রেডঃ ১ম",
                    fontWeight = FontWeight.W400,
                    textSize = 8.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
                CustomTextComponents(
                    title = ". পদবিঃ ক্যাডার",
                    fontWeight = FontWeight.W400,
                    textSize = 8.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
            }
        }

        OutlineBorderWithText(
            index = "A",
            title = " ঢাক্ ঢাক্ গুড় গুড় করে কী লাভ,কাজে \nলেগে যাও",
            answered = isAnswerGiven,
            backGroundColor = isRightA,
            textColor = isRightAText
        ) { index, isAnswered ->
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isAnswerGiven = true
                    isRightA = greenTextColor
                    isRightAText = whiteColor
                } else {
                    when (rightAnswer) {
                        "B" -> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "B",
            " ঢাক্ ঢাক্ গুড় গুড় করে কী লাভ,কাজে \nলেগে যাও",
            answered = isAnswerGiven,
            backGroundColor = isRightB,
            textColor = isRightBText
        ) { index, isAnswered ->

            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isAnswerGiven = true
                    isRightB = greenTextColor
                    isRightBText = whiteColor
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "C",
            " ঢাক্ ঢাক্ গুড় গুড় করে কী লাভ,কাজে \nলেগে যাও",
            answered = isAnswerGiven,
            backGroundColor = isRightC,
            textColor = isRightCText
        ) { index, isAnswered ->

            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isAnswerGiven = true
                    isRightC = greenTextColor
                    isRightCText = whiteColor
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                        }

                        "B"-> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "D",
            " ঢাক্ ঢাক্ গুড় গুড় করে কী লাভ,কাজে \nলেগে যাও",
            answered = isAnswerGiven,
            backGroundColor = isRightD,
            textColor = isRightDText
        ) { index, isAnswered ->
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isAnswerGiven = true
                    isRightD = greenTextColor
                    isRightDText = whiteColor
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                        }

                        "B" -> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Preview
@Composable
fun sdsd() {
    QuestionDetailsComponent()
}