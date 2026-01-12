package com.example.composecleanarchitecture.ui.features.previousyear_question.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecleanarchitecture.models.AnswerTrackingState
import com.example.composecleanarchitecture.models.previous_year_question.PrevYerBCSQusQuizDetailsAttributes
import com.example.composecleanarchitecture.ui.common_components.CommonMathViewTextComponent
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.common_components.QuestionDetailsExplanationComponents
import com.example.composecleanarchitecture.ui.features.question_details.components.OutlineBorderWithText
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.greenTextColor
import com.example.composecleanarchitecture.ui.theme.optionTextColor
import com.example.composecleanarchitecture.ui.theme.outlineBorderColor
import com.example.composecleanarchitecture.ui.theme.questionDetailsTextColor
import com.example.composecleanarchitecture.ui.theme.redTextColor
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun BcsQuestionDetailsComponent(
    quesIndex: Int,
    attributes: PrevYerBCSQusQuizDetailsAttributes,
    selectedPosition: AnswerTrackingState?,
    onAnswerGiven: (isGiven: Boolean, answerValue: Int, AnswerTrackingState) -> Unit
) {
    val rightAnswer = attributes.question?.data?.attributes?.answer?.uppercase()
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
    var expanded by remember { mutableStateOf(false) }
    if (selectedPosition != null) {
        when (selectedPosition.rightAnsNo) {
            1 -> {
                isRightA = greenTextColor
            }

            2 -> {
                isRightB = greenTextColor
            }

            3 -> {
                isRightC = greenTextColor
            }

            4 -> {
                isRightD = greenTextColor
            }

            5 -> {
                isRightE = greenTextColor
            }
        }
        when (selectedPosition.wrongAnsNo) {
            1 -> {
                isRightA = redTextColor
            }

            2 -> {
                isRightB = redTextColor
            }

            3 -> {
                isRightC = redTextColor
            }

            4 -> {
                isRightD = redTextColor
            }

            5 -> {
                isRightE = redTextColor
            }
        }
        isAnswerGiven = selectedPosition.isAnswerGiven
    }
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
                    title = "${quesIndex + 1} . ",
                    fontWeight = FontWeight.W400,
                    textSize = 14.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )
                CommonMathViewTextComponent(
                    title = "${attributes.question?.data?.attributes?.question}",
                    textColor = blackVarColor1,
                )
            }
            Column {
                Icon(
                    Icons.Default.MoreHoriz,
                    contentDescription = null,
                    tint = activeTextColor,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            expanded = !expanded
                        }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(whiteColor)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                "Issue of this question or answer.",
                                color = blackVarColor1
                            )
                        },
                        onClick = { expanded = !expanded }
                    )
                    DropdownMenuItem(
                        text = { Text("Save as my question.", color = blackVarColor1) },
                        onClick = { expanded = !expanded }
                    )
                }
            }

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
                    title = ". সালঃ ${attributes.year}",
                    fontWeight = FontWeight.W400,
                    textSize = 6.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
                CustomTextComponents(
                    title = ". ${attributes.organization?.data?.attributes?.name}",
                    fontWeight = FontWeight.W400,
                    textSize = 6.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
            }
            Column {
                CustomTextComponents(
                    title = ". গ্রেডঃ ${attributes.grade?.data?.attributes?.name}",
                    fontWeight = FontWeight.W400,
                    textSize = 6.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
                CustomTextComponents(
                    title = ". পদবিঃ ${attributes.designation?.data?.attributes?.name}",
                    fontWeight = FontWeight.W400,
                    textSize = 6.sp,
                    textColor = questionDetailsTextColor,
                    textAlign = TextAlign.Start
                )
            }
        }

        OutlineBorderWithText(
            index = "A",
            title = " ${attributes.question?.data?.attributes?.optionA}",
            answered = isAnswerGiven,
            backGroundColor = isRightA,
            textColor = isRightAText,
            qusTopics = "${attributes.subject?.data?.attributes?.name?.trim()}"
        ) { index, _ ->
            var isRightAnswer = false
            var answerValue = 0
            var rightAnsIndex = 0
            var wrongAnsIndex = 0
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isRightAnswer = true
                    answerValue = 1
                    isAnswerGiven = true
                    isRightA = greenTextColor
                    isRightAText = whiteColor
                    rightAnsIndex = 1
                    wrongAnsIndex = 1
                } else {
                    when (rightAnswer) {
                        "B" -> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                            rightAnsIndex = 1
                            wrongAnsIndex = 2
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                            rightAnsIndex = 1
                            wrongAnsIndex = 3
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                            rightAnsIndex = 1
                            wrongAnsIndex = 4
                        }

                        "E" -> {
                            isAnswerGiven = true
                            isRightE = greenTextColor
                            isRightEText = whiteColor
                            isRightA = redTextColor
                            isRightAText = whiteColor
                            rightAnsIndex = 1
                            wrongAnsIndex = 5
                        }
                    }
                }
                onAnswerGiven.invoke(
                    isRightAnswer, answerValue, AnswerTrackingState(
                        questionNo = quesIndex + 1,
                        rightAnsNo = rightAnsIndex,
                        wrongAnsNo = wrongAnsIndex,
                        isAnswerGiven = isAnswerGiven
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "B",
            " ${attributes.question?.data?.attributes?.optionB}",
            answered = isAnswerGiven,
            backGroundColor = isRightB,
            textColor = isRightBText
        ) { index, _ ->

            var isRightAnswer = false
            var answerValue = 0
            var rightAnsIndex = 0
            var wrongAnsIndex = 0
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isRightAnswer = true
                    answerValue = 1
                    isAnswerGiven = true
                    isRightB = greenTextColor
                    isRightBText = whiteColor
                    rightAnsIndex = 2
                    wrongAnsIndex = 2
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                            rightAnsIndex = 2
                            wrongAnsIndex = 1
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                            rightAnsIndex = 2
                            wrongAnsIndex = 3
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                            rightAnsIndex = 2
                            wrongAnsIndex = 4
                        }

                        "E" -> {
                            isAnswerGiven = true
                            isRightE = greenTextColor
                            isRightEText = whiteColor
                            isRightB = redTextColor
                            isRightBText = whiteColor
                            rightAnsIndex = 2
                            wrongAnsIndex = 5
                        }
                    }
                }
                onAnswerGiven.invoke(
                    isRightAnswer, answerValue, AnswerTrackingState(
                        questionNo = quesIndex + 1,
                        rightAnsNo = rightAnsIndex,
                        wrongAnsNo = wrongAnsIndex,
                        isAnswerGiven = isAnswerGiven
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "C",
            " ${attributes.question?.data?.attributes?.optionC}",
            answered = isAnswerGiven,
            backGroundColor = isRightC,
            textColor = isRightCText
        ) { index, _ ->

            var isRightAnswer = false
            var answerValue = 0
            var rightAnsIndex = 0
            var wrongAnsIndex = 0
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isRightAnswer = true
                    answerValue = 1
                    isAnswerGiven = true
                    isRightC = greenTextColor
                    isRightCText = whiteColor
                    rightAnsIndex = 3
                    wrongAnsIndex = 3
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                            rightAnsIndex = 3
                            wrongAnsIndex = 1
                        }

                        "B" -> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                            rightAnsIndex = 3
                            wrongAnsIndex = 2
                        }

                        "D" -> {
                            isAnswerGiven = true
                            isRightD = greenTextColor
                            isRightDText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                            rightAnsIndex = 3
                            wrongAnsIndex = 4
                        }

                        "E" -> {
                            isAnswerGiven = true
                            isRightE = greenTextColor
                            isRightEText = whiteColor
                            isRightC = redTextColor
                            isRightCText = whiteColor
                            rightAnsIndex = 3
                            wrongAnsIndex = 5
                        }
                    }
                }
                onAnswerGiven.invoke(
                    isRightAnswer, answerValue, AnswerTrackingState(
                        questionNo = quesIndex + 1,
                        rightAnsNo = rightAnsIndex,
                        wrongAnsNo = wrongAnsIndex,
                        isAnswerGiven = isAnswerGiven
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlineBorderWithText(
            "D",
            " ${attributes.question?.data?.attributes?.optionD}",
            answered = isAnswerGiven,
            backGroundColor = isRightD,
            textColor = isRightDText
        ) { index, _ ->
            var isRightAnswer = false
            var answerValue = 0
            var rightAnsIndex = 0
            var wrongAnsIndex = 0
            if (!isAnswerGiven) {
                if (index == rightAnswer) {
                    isRightAnswer = true
                    answerValue = 1
                    isAnswerGiven = true
                    isRightD = greenTextColor
                    isRightDText = whiteColor
                    rightAnsIndex = 4
                    wrongAnsIndex = 4
                } else {
                    when (rightAnswer) {
                        "A" -> {
                            isAnswerGiven = true
                            isRightA = greenTextColor
                            isRightAText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                            rightAnsIndex = 4
                            wrongAnsIndex = 1
                        }

                        "B" -> {
                            isAnswerGiven = true
                            isRightB = greenTextColor
                            isRightBText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                            rightAnsIndex = 4
                            wrongAnsIndex = 2
                        }

                        "C" -> {
                            isAnswerGiven = true
                            isRightC = greenTextColor
                            isRightCText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                            rightAnsIndex = 4
                            wrongAnsIndex = 3
                        }

                        "E" -> {
                            isAnswerGiven = true
                            isRightE = greenTextColor
                            isRightEText = whiteColor
                            isRightD = redTextColor
                            isRightDText = whiteColor
                            rightAnsIndex = 4
                            wrongAnsIndex = 5
                        }
                    }
                }
                onAnswerGiven.invoke(
                    isRightAnswer, answerValue, AnswerTrackingState(
                        questionNo = quesIndex + 1,
                        rightAnsNo = rightAnsIndex,
                        wrongAnsNo = wrongAnsIndex,
                        isAnswerGiven = isAnswerGiven
                    )
                )
            }
        }
        if (!attributes.question?.data?.attributes?.optionE.isNullOrEmpty() || attributes.question?.data?.attributes?.optionE != "") {
            Spacer(modifier = Modifier.height(10.dp))
            OutlineBorderWithText(
                "E",
                " ${attributes.question?.data?.attributes?.optionE}",
                answered = isAnswerGiven,
                backGroundColor = isRightE,
                textColor = isRightEText
            ) { index, _ ->
                var isRightAnswer = false
                var answerValue = 0
                var rightAnsIndex = 0
                var wrongAnsIndex = 0
                if (!isAnswerGiven) {
                    if (index == rightAnswer) {
                        isRightAnswer = true
                        answerValue = 1
                        isAnswerGiven = true
                        isRightE = greenTextColor
                        isRightEText = whiteColor
                        rightAnsIndex = 5
                        wrongAnsIndex = 5
                    } else {
                        when (rightAnswer) {
                            "A" -> {
                                isAnswerGiven = true
                                isRightA = greenTextColor
                                isRightAText = whiteColor
                                isRightE = redTextColor
                                isRightEText = whiteColor
                                rightAnsIndex = 5
                                wrongAnsIndex = 1
                            }

                            "B" -> {
                                isAnswerGiven = true
                                isRightB = greenTextColor
                                isRightBText = whiteColor
                                isRightE = redTextColor
                                isRightEText = whiteColor
                                rightAnsIndex = 5
                                wrongAnsIndex = 2
                            }

                            "C" -> {
                                isAnswerGiven = true
                                isRightC = greenTextColor
                                isRightCText = whiteColor
                                isRightE = redTextColor
                                isRightEText = whiteColor
                                rightAnsIndex = 5
                                wrongAnsIndex = 3
                            }

                            "D" -> {
                                isAnswerGiven = true
                                isRightD = greenTextColor
                                isRightDText = whiteColor
                                isRightE = redTextColor
                                isRightEText = whiteColor
                                rightAnsIndex = 5
                                wrongAnsIndex = 4
                            }
                        }
                    }
                    onAnswerGiven.invoke(
                        isRightAnswer, answerValue, AnswerTrackingState(
                            questionNo = quesIndex + 1,
                            rightAnsNo = rightAnsIndex,
                            wrongAnsNo = wrongAnsIndex,
                            isAnswerGiven = isAnswerGiven
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (isAnswerGiven) {
            Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                QuestionDetailsExplanationComponents("${attributes.question?.data?.attributes?.details}")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}