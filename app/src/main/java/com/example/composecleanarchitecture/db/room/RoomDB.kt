package com.example.composecleanarchitecture.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composecleanarchitecture.db.room.dao.QuestionCategoryDao
import com.example.composecleanarchitecture.db.room.entity.QuestionCategory
import com.example.composecleanarchitecture.models.cat_wise_organisations.PvsErIBAPscOrgName
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.models.designations.DesignationDataModel
import com.example.composecleanarchitecture.models.institute_qus_year.YearsOfBankInsEduForPvsErQues
import com.example.composecleanarchitecture.models.previous_year_question.FormatQuestionsAsExpected
import com.example.composecleanarchitecture.models.previous_year_question.PreviousYearQuestionDetailsFormat
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.models.tomo_sequence.BCSTomoDataFormation
import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseDataX

@Database(
    entities = [
        QuestionCategory::class,
        PreviousYearQuestion::class,
        OrganizationCategory::class,
        SubjectListResponseDataX::class,
        TopicsListResponseDataX::class,
        SubTopicsListResponseDataX::class,
        FormatQuestionsAsExpected::class,
        DesignationDataModel::class,
        BCSTomoDataFormation::class,
        PreviousYearQuestionDetailsFormat::class,
        YearsOfBankInsEduForPvsErQues::class,
        PvsErIBAPscOrgName::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun questionCategoryDao(): QuestionCategoryDao
}