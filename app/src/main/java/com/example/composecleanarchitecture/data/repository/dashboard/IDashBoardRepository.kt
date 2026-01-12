package com.example.composecleanarchitecture.data.repository.dashboard

import com.example.composecleanarchitecture.base.IBaseRepository
import com.example.composecleanarchitecture.data.network.AppNetworkState
import com.example.composecleanarchitecture.models.dashboard.organization_wise.DashOrgWiseCategoryResponse
import com.example.composecleanarchitecture.models.dashboard.previous_year.DashBoardPrevErQuest
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.models.tomo_sequence.DataX
import com.example.composecleanarchitecture.models.tomo_sequence.TomoBcsSequenceResponse
import kotlinx.coroutines.flow.Flow

interface IDashBoardRepository : IBaseRepository {

    suspend fun getDashBoardPrevYearQuestion(): Flow<AppNetworkState<DashBoardPrevErQuest>>
    suspend fun getPvsErQusCatFromRoom(): Flow<List<PreviousYearQuestion>>
    suspend fun getDashBoardOrganizationQuestion(): Flow<AppNetworkState<DashOrgWiseCategoryResponse>>
    suspend fun getDashBoardOrganizationQuestionRoom(): Flow<List<OrganizationCategory>>
    suspend fun getCategoryTomoSequence(categoryCode:String): Flow<AppNetworkState<ArrayList<DataX>>>
    suspend fun savePreviousYearCategories(pvsErQusCategory:List<PreviousYearQuestion>):List<Long>
    suspend fun saveDashOrgCategory(pvsErQusCategory:List<OrganizationCategory>):List<Long>
}