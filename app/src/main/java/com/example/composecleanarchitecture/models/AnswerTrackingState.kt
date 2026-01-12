package com.example.composecleanarchitecture.models

data class AnswerTrackingState(
    val questionNo: Int,
    val rightAnsNo: Int,
    val wrongAnsNo: Int,
    val isAnswerGiven: Boolean
)