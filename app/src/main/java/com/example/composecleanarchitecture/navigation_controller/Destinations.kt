package com.example.composecleanarchitecture.navigation_controller


sealed class Destinations(val route: String) {
    data object SplashScreen : Destinations("SplashScreen")
    data object LoginScreen : Destinations("LoginScreen")
    data object DashBoardScreen : Destinations("DashBoardScreen")
    data object QuestionDetailsScreen : Destinations("QuestionDetailsScreen")
    data object AllTopicsListScreen : Destinations("AllTopicsListScreen/{subject_code}")
    data object AllSubjectListScreen : Destinations("AllSubjectListScreen")
    data object AllPreviousYearCategory : Destinations("AllPreviousYearCategory")
    data object AllOrganizationListScreen : Destinations("AllOrganizationListScreen")
    data object CourseOutletScreen : Destinations("CourseOutletScreen")
    data object SequenceByTomoScreen : Destinations("SequenceByTomoScreen/{category_Code}")
    data object AllSubtopicsListScreen : Destinations("AllSubtopicsListScreen/{subject_code}/{topics_code}")
    data object BcsPrevYerQuesDetailsScreen :
        Destinations("BcsPrevYerQuesDetailsScreen/{sequence}/{category_Code}/{year}")

    data object BankEduInstCatItemByYear : Destinations("BankEduInstCatItemByYear/{category_Code}")

    data object BankEduInstYearWiseQuesSplitItemScreen :
        Destinations("BankEduInstYearWiseQuesSplitItemScreen/{category_code}/{year}")

    data object PvsErQusCatErWiseItemListDetailsScreen :
        Destinations("PvsErQusCatErWiseItemListDetailsScreen")

    data object TopicsQuestionDetailsScreen :
        Destinations("TopicsQuestionDetailsScreen")

    data object IBAPSCOrganizationsScreen :
        Destinations("IBAPSCOrganizationsScreen/{category_code}")

    data object OrgWiseIbaPasQuestionSplitItemScreen :
        Destinations("OrgWiseIbaPasQuestionSplitItemScreen/{category_code}/{organization_code}")

    data object OrgWiseDesignationListScreen :
        Destinations("OrgWiseDesignationListScreen/{organization_code}")

    data object TopicsWiseQuestionListScreen :
        Destinations("TopicsWiseQuestionListScreen/{query_code}/{subtopic_code}")

    data object PvsErQusByDesgOrgListScreen :
        Destinations("PvsErQusByDesgOrgListScreen/{organization_code}/{designation_code}")

    data object PrevErQuesByYearSplitItemScreen :
        Destinations("PrevErQuesByYearSplitItemScreen/{year}")
    data object AllYearCardListScreen :
        Destinations("AllYearCardListScreen")

    data object CourseExamScreen :
        Destinations("CourseExamScreen")
    data object AllCoursesScreen :
        Destinations("AllCoursesScreen")

}

