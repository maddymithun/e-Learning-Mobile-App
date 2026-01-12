package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseAttributes(
    @SerializedName("answer")
    val answer: String? = "",
    @SerializedName("course")
    val course: CourseQuestionListResponseCourse? = CourseQuestionListResponseCourse(),
    @SerializedName("option_a")
    val optionA: String? = "",
    @SerializedName("option_b")
    val optionB: String? = "",
    @SerializedName("option_c")
    val optionC: String? = "",
    @SerializedName("option_d")
    val optionD: String? = "",
    @SerializedName(" option_e")
    val optionE: String? = "",
    @SerializedName("question")
    val question: String? = ""
)