package com.example.composecleanarchitecture.models.courses.course_questions

data class UserProvidedAnswerModel(
    val isAnswerGiven: Boolean,
    val answeredOption: String,
    val questionNumber: String,
    val courseCode: String,
    val syllabusCode: String,
    val question: String,
    val rightAnswer: String
)