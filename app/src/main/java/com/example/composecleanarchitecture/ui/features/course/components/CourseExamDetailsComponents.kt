package com.example.composecleanarchitecture.ui.features.course.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.example.composecleanarchitecture.models.courses.course_questions.CourseQuestionListResponseDataX
import com.example.composecleanarchitecture.models.courses.course_questions.UserProvidedAnswerModel
import com.example.composecleanarchitecture.ui.common_components.CommonMathViewTextComponent
import com.example.composecleanarchitecture.ui.common_components.CustomTextComponents
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.greenTextColor
import com.example.composecleanarchitecture.ui.theme.outlineBorderColor
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun CourseExamDetailsComponents(
    index: Int,
    courseCode: String,
    attributes: CourseQuestionListResponseDataX,
    onAnswerGiven: (Int, UserProvidedAnswerModel) -> Unit
) {

    var isAnswerGiven by remember { mutableStateOf(false) }
    var isRightA by remember { mutableStateOf(outlineBorderColor) }
    var isRightB by remember { mutableStateOf(outlineBorderColor) }
    var isRightC by remember { mutableStateOf(outlineBorderColor) }
    var isRightD by remember { mutableStateOf(outlineBorderColor) }
    var isRightE by remember { mutableStateOf(outlineBorderColor) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)

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
                    title = "${index + 1} . ",
                    fontWeight = FontWeight.W400,
                    textSize = 14.sp,
                    textColor = blackVarColor1,
                    textAlign = TextAlign.Start
                )
                CommonMathViewTextComponent(
                    title = "${attributes.attributes?.question}",
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


        CourseOptOutlineTxtBorderComponents(
            index = "A",
            title = " ${attributes.attributes?.optionA}",
            answered = isAnswerGiven,
            backGroundColor = isRightA,
        ) { _, _ ->
            if (!isAnswerGiven) {
                isAnswerGiven = true
                isRightA = greenTextColor
                onAnswerGiven.invoke(
                    index - 1,
                    UserProvidedAnswerModel(
                        isAnswerGiven = isAnswerGiven,
                        answeredOption = "A",
                        questionNumber = "${attributes.id}",
                        courseCode = courseCode,
                        syllabusCode = "${attributes.attributes?.course?.data?.attributes?.code}",
                        question = "${attributes.attributes?.question}",
                        rightAnswer = "${attributes.attributes?.answer}"
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        CourseOptOutlineTxtBorderComponents(
            "B",
            " ${attributes.attributes?.optionB}",
            answered = isAnswerGiven,
            backGroundColor = isRightB
        ) { _, _ ->

            if (!isAnswerGiven) {
                isAnswerGiven = true
                isRightB = greenTextColor
                onAnswerGiven.invoke(
                    index - 1,
                    UserProvidedAnswerModel(
                        isAnswerGiven = isAnswerGiven,
                        answeredOption = "B",
                        questionNumber = "${attributes.id}",
                        courseCode = courseCode,
                        syllabusCode = "${attributes.attributes?.course?.data?.attributes?.code}",
                        question = "${attributes.attributes?.question}",
                        rightAnswer = "${attributes.attributes?.answer}"
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        CourseOptOutlineTxtBorderComponents(
            "C",
            " ${attributes.attributes?.optionC}",
            answered = isAnswerGiven,
            backGroundColor = isRightC
        ) { _, _ ->

            if (!isAnswerGiven) {
                isAnswerGiven = true
                isRightC = greenTextColor
                onAnswerGiven.invoke(
                    index- 1,
                    UserProvidedAnswerModel(
                        isAnswerGiven = isAnswerGiven,
                        answeredOption = "C",
                        questionNumber = "${attributes.id}",
                        courseCode = courseCode,
                        syllabusCode = "${attributes.attributes?.course?.data?.attributes?.code}",
                        question = "${attributes.attributes?.question}",
                        rightAnswer = "${attributes.attributes?.answer}"
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    CourseOptOutlineTxtBorderComponents(
        "D",
        " ${attributes.attributes?.optionD}",
        answered = isAnswerGiven,
        backGroundColor = isRightD
    ) { _, _ ->
        if (!isAnswerGiven) {
            isAnswerGiven = true
            isRightD = greenTextColor
            onAnswerGiven.invoke(
                index- 1,
                UserProvidedAnswerModel(
                    isAnswerGiven = isAnswerGiven,
                    answeredOption = "D",
                    questionNumber = "${attributes.id}",
                    courseCode = courseCode,
                    syllabusCode = "${attributes.attributes?.course?.data?.attributes?.code}",
                    question = "${attributes.attributes?.question}",
                    rightAnswer = "${attributes.attributes?.answer}"
                )
            )
        }
    }

    if (attributes.attributes?.optionE!=""||!attributes.attributes.optionE.isNullOrEmpty()){
        Spacer(modifier = Modifier.height(10.dp))
        CourseOptOutlineTxtBorderComponents(
            "E",
            " ${attributes.attributes?.optionE}",
            answered = isAnswerGiven,
            backGroundColor = isRightE
        ) { _, _ ->
            if (!isAnswerGiven) {
                isAnswerGiven = true
                isRightE = greenTextColor
                onAnswerGiven.invoke(
                    index- 1,
                    UserProvidedAnswerModel(
                        isAnswerGiven = isAnswerGiven,
                        answeredOption = "E",
                        questionNumber = "${attributes.id}",
                        courseCode = courseCode,
                        syllabusCode = "${attributes.attributes?.course?.data?.attributes?.code}",
                        question = "${attributes.attributes?.question}",
                        rightAnswer = "${attributes.attributes?.answer}"
                    )
                )
            }
        }
    }


    Spacer(modifier = Modifier.height(10.dp))
}

