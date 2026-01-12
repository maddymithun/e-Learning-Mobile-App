package com.example.composecleanarchitecture.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionCategoryDao {

    @Insert
    abstract fun insert(categories: List<QuestionCategory>): List<Long>

    @Insert
    abstract fun insertPvsErCategory(categories: List<PreviousYearQuestion>): List<Long>

    @Query("SELECT * FROM PreviousYearQuestion")
    abstract fun getPvsErCategory(): Flow<List<PreviousYearQuestion>>

    @Insert
    abstract fun insertDashOrgCategory(categories: List<OrganizationCategory>): List<Long>

    @Query("SELECT * FROM OrganizationCategory")
    abstract fun getDashOrgCategory(): Flow<List<OrganizationCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDashSubjectListCategory(categories: List<SubjectListResponseDataX>): List<Long>

    @Query("SELECT * FROM SubjectListResponseDataX ORDER BY id ASC LIMIT :limit OFFSET :offset")
    abstract fun getDashSubjectListCategory(
        limit: Int,
        offset: Int
    ): List<SubjectListResponseDataX>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTopicsOfSubject(categories: List<TopicsListResponseDataX>): List<Long>

    @Query("SELECT * FROM TopicsListResponseDataX where subjectCode= :code  ORDER BY id ASC LIMIT :limit OFFSET :offset")
    abstract fun getTopicsOfSubject(
        code: String,
        limit: Int,
        offset: Int
    ): List<TopicsListResponseDataX>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSubtopicsOfTopicsAndSubject(categories: List<SubTopicsListResponseDataX>): List<Long>

    @Query("SELECT * FROM SubTopicsListResponseDataX where subjectCode= :subjectCode AND topicsCode= :topicsCode  ORDER BY id ASC LIMIT :limit OFFSET :offset")
    abstract fun getSubtopicsOfTopicsAndSubject(
        subjectCode: String,
        topicsCode: String,
        limit: Int,
        offset: Int
    ): List<SubTopicsListResponseDataX>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDesignationOfOrg(categories: List<DesignationDataModel>): List<Long>

    @Query("SELECT * FROM DesignationDataModel where categoryCode= :queryCode  ORDER BY columnId ASC LIMIT :limit OFFSET :offset")
    abstract fun getDesignationOfOrg(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<DesignationDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPvsErQuestionAsExpectedFormat(categories: List<FormatQuestionsAsExpected>): List<Long>

    @Query("DELETE  FROM FormatQuestionsAsExpected WHERE queryCode= :queryCode")
    abstract fun deletePvsErQuestionAsExpectedFormat(queryCode: String): Int

    @Query("SELECT * FROM FormatQuestionsAsExpected where queryCode= :queryCode  ORDER BY id ASC LIMIT :limit OFFSET :offset")
    abstract fun getPvsErQuestionAsExpectedFormat(
        queryCode: String,
        limit: Int,
        offset: Int
    ): List<FormatQuestionsAsExpected>

    @Insert
    abstract fun insertBcsTomoData(bcsSequence: List<BCSTomoDataFormation>): List<Long>

    @Query("SELECT * FROM BCSTomoDataFormation where categoryCode = :categoryCode")
    abstract fun getBcsTomoDataList(categoryCode: String): Flow<List<BCSTomoDataFormation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBcsAndJudgeQuestion(categories: List<PreviousYearQuestionDetailsFormat>): List<Long>

    @Query("DELETE  FROM PreviousYearQuestionDetailsFormat WHERE queryCode= :queryCode")
    abstract fun deleteBcsAndJudgeQuestion(queryCode: String): Int

    @Query("SELECT * FROM PreviousYearQuestionDetailsFormat where queryCode = :categoryCode  ORDER BY columnId ASC LIMIT :limit OFFSET :offset")
    abstract fun getBcsAndJudgeQuestionList(
        categoryCode: String, limit: Int,
        offset: Int
    ): List<PreviousYearQuestionDetailsFormat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertYearListPvsErQuesInsBankIns(categories: List<YearsOfBankInsEduForPvsErQues>): List<Long>

    @Query("DELETE  FROM YearsOfBankInsEduForPvsErQues WHERE queryCode= :queryCode")
    abstract fun deleteYearListPvsErQuesInsBankIns(queryCode: String): Int

    @Query("SELECT * FROM YearsOfBankInsEduForPvsErQues where queryCode = :categoryCode")
    abstract fun getYearListPvsErQuesInsBankIns(
        categoryCode: String
    ): Flow<List<YearsOfBankInsEduForPvsErQues>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertYOrgListForIbaPscPvsEr(categories: List<PvsErIBAPscOrgName>): List<Long>

    @Query("DELETE  FROM PvsErIBAPscOrgName WHERE queryCode= :queryCode")
    abstract fun deleteOrgListForIbaPscPvsEr(queryCode: String): Int

    @Query("SELECT * FROM PvsErIBAPscOrgName where queryCode = :categoryCode")
    abstract fun getOrgListForIbaPscPvsEr(
        categoryCode: String
    ): Flow<List<PvsErIBAPscOrgName>>
}