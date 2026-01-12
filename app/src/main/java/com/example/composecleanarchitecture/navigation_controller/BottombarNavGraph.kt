package com.example.composecleanarchitecture.navigation_controller

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composecleanarchitecture.ui.features.circular.CircularScreen
import com.example.composecleanarchitecture.ui.features.course.AllCoursesScreen
import com.example.composecleanarchitecture.ui.features.course.CourseExamScreen
import com.example.composecleanarchitecture.ui.features.course.CourseOutletScreen
import com.example.composecleanarchitecture.ui.features.home.HomeScreen
import com.example.composecleanarchitecture.ui.features.home.components.PreviousYear.AllPreviousYearCategory
import com.example.composecleanarchitecture.ui.features.my_question.MyQuestionsScreen
import com.example.composecleanarchitecture.ui.features.organization_wise_question.AllOrganizationListScreen
import com.example.composecleanarchitecture.ui.features.organization_wise_question.OrgWiseDesignationListScreen
import com.example.composecleanarchitecture.ui.features.organization_wise_question.PvsErQusByDesgOrgListScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.BankEduInstCatItemByYear
import com.example.composecleanarchitecture.ui.features.previousyear_question.BankEduInstYearWiseQuesSplitItemScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.BcsPrevYerQuesDetailsScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.IBAPSCOrganizationsScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.OrgWiseIbaPasQuestionSplitItemScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.PvsErQusCatErWiseItemListDetailsScreen
import com.example.composecleanarchitecture.ui.features.previousyear_question.SequenceByTomoScreen
import com.example.composecleanarchitecture.ui.features.question_bank.QuestionBankScreen
import com.example.composecleanarchitecture.ui.features.question_details.QuestionDetailsScreen
import com.example.composecleanarchitecture.ui.features.subject_wise.AllSubjectListScreen
import com.example.composecleanarchitecture.ui.features.subject_wise.AllSubtopicsListScreen
import com.example.composecleanarchitecture.ui.features.subject_wise.AllTopicsListScreen
import com.example.composecleanarchitecture.ui.features.subject_wise.TopicsWiseQuestionListScreen
import com.example.composecleanarchitecture.ui.features.subject_wise.components.TopicsQuestionDetailsScreen
import com.example.composecleanarchitecture.ui.features.year_wise_question.AllYearCardListScreen
import com.example.composecleanarchitecture.ui.features.year_wise_question.PrevErQuesByYearSplitItemScreen
import com.example.composecleanarchitecture.view_models.SharedViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun BottomBarNavGraph(
    navController: NavController,
    logout: () -> Unit
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomBarDestination.HomeScreen.route
    ) {
        composable(BottomBarDestination.HomeScreen.route) {
            HomeScreen(
                sharedViewModel = sharedViewModel,
                navController = navController
            )
        }
        composable(BottomBarDestination.QuestionBankScreen.route) { QuestionBankScreen(navController = navController) }
        composable(BottomBarDestination.MyQuestionsScreen.route) { MyQuestionsScreen(navController = navController) }
        composable(BottomBarDestination.CircularScreen.route) {
            CircularScreen() {
                logout()
            }
        }
        composable(Destinations.QuestionDetailsScreen.route) { QuestionDetailsScreen(navController) }
        composable(Destinations.AllPreviousYearCategory.route) {
            AllPreviousYearCategory(
                navController
            )
        }
        composable(Destinations.AllOrganizationListScreen.route) {
            AllOrganizationListScreen(
                navController
            )
        }
        composable(Destinations.CourseOutletScreen.route) {
            CourseOutletScreen(sharedViewModel, navController)
        }
        composable(
            Destinations.SequenceByTomoScreen.route,
            arguments = listOf(navArgument("category_Code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("category_Code")
            SequenceByTomoScreen(navController, value ?: "")
        }

        composable(
            Destinations.BcsPrevYerQuesDetailsScreen.route,
            arguments = listOf(navArgument("sequence") { type = NavType.StringType },
                navArgument("category_Code") { type = NavType.StringType },
                navArgument("year") { type = NavType.StringType })
        ) { backStackEntry ->
            val sequence = backStackEntry.arguments?.getString("sequence")
            val categoryCode = backStackEntry.arguments?.getString("category_Code")
            val year = backStackEntry.arguments?.getString("year")
            BcsPrevYerQuesDetailsScreen(
                sequence = sequence ?: "",
                categoryCode = categoryCode ?: "",
                year = year?.toInt() ?: 0,
                navController = navController
            )
        }
        composable(
            Destinations.BankEduInstCatItemByYear.route,
            arguments = listOf(navArgument("category_Code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("category_Code")
            BankEduInstCatItemByYear(value ?: "", navController)
        }

        composable(
            Destinations.BankEduInstYearWiseQuesSplitItemScreen.route,
            arguments = listOf(
                navArgument("category_code") { type = NavType.StringType },
                navArgument("year") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryCode = backStackEntry.arguments?.getString("category_code")
            val year = backStackEntry.arguments?.getString("year")
            BankEduInstYearWiseQuesSplitItemScreen(
                sharedViewModel,
                categoryCode = categoryCode ?: "",
                year = year ?: "0",
                navController = navController
            )
        }
        composable(
            Destinations.OrgWiseIbaPasQuestionSplitItemScreen.route,
            arguments = listOf(
                navArgument("category_code") { type = NavType.StringType },
                navArgument("organization_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryCode = backStackEntry.arguments?.getString("category_code")
            val organizationCode = backStackEntry.arguments?.getString("organization_code")
            OrgWiseIbaPasQuestionSplitItemScreen(
                sharedViewModel,
                categoryCode = categoryCode ?: "",
                organizationCode = organizationCode ?: "0",
                navController = navController
            )
        }

        composable(
            Destinations.PvsErQusCatErWiseItemListDetailsScreen.route,
        ) { _ ->
            PvsErQusCatErWiseItemListDetailsScreen(sharedViewModel, navController)
        }

        composable(
            Destinations.IBAPSCOrganizationsScreen.route,
            arguments = listOf(navArgument("category_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("category_code")
            IBAPSCOrganizationsScreen(value ?: "", navController)
        }

        composable(
            Destinations.OrgWiseDesignationListScreen.route,
            arguments = listOf(navArgument("organization_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("organization_code")
            OrgWiseDesignationListScreen(value ?: "", navController)
        }

        composable(
            Destinations.TopicsWiseQuestionListScreen.route,
            arguments = listOf(navArgument("query_code") { type = NavType.StringType },
                navArgument("subtopic_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("subtopic_code")
            val queryCode = backStackEntry.arguments?.getString("query_code")
            TopicsWiseQuestionListScreen(value ?: "", queryCode ?: "", navController)
        }

        composable(
            Destinations.CourseExamScreen.route
        ) { _ ->

            CourseExamScreen(navController, sharedViewModel)
        }
        composable(
            Destinations.AllCoursesScreen.route
        ) { _ ->
            AllCoursesScreen(navController, sharedViewModel)
        }
        composable(
            Destinations.AllTopicsListScreen.route,
            arguments = listOf(navArgument("subject_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("subject_code")
            AllTopicsListScreen(value ?: "", navController, sharedViewModel)
        }

        composable(
            Destinations.PrevErQuesByYearSplitItemScreen.route,
            arguments = listOf(navArgument("year") { type = NavType.StringType })
        ) { backStackEntry ->
            val value = backStackEntry.arguments?.getString("year")
            PrevErQuesByYearSplitItemScreen(sharedViewModel, value ?: "", navController)
        }
        composable(
            Destinations.AllYearCardListScreen.route
        ) { _ ->
            AllYearCardListScreen(navController)
        }

        composable(
            Destinations.AllSubjectListScreen.route
        ) { _ ->
            AllSubjectListScreen(navController)
        }

        composable(
            Destinations.PvsErQusByDesgOrgListScreen.route,
            arguments = listOf(navArgument("organization_code") { type = NavType.StringType },
                navArgument("designation_code") { type = NavType.StringType })
        ) { backStackEntry ->
            val organizationCode = backStackEntry.arguments?.getString("organization_code")
            val designationCode = backStackEntry.arguments?.getString("designation_code")
            PvsErQusByDesgOrgListScreen(
                sharedViewModel,
                organizationCode ?: "",
                designationCode ?: "",
                navController
            )
        }

        composable(Destinations.TopicsQuestionDetailsScreen.route) {
            TopicsQuestionDetailsScreen(
                sharedViewModel,
                navController
            )
        }

        composable(
            Destinations.AllSubtopicsListScreen.route,
            arguments = listOf(navArgument("subject_code") { type = NavType.StringType },
                navArgument("topics_code") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val topicsCode = backStackEntry.arguments?.getString("topics_code")
            val subjectCode = backStackEntry.arguments?.getString("subject_code")
            AllSubtopicsListScreen(subjectCode ?: "", topicsCode ?: "", navController)
        }
    }
}