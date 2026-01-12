package com.example.composecleanarchitecture.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.base.UIState
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.courses.CourseListWithTopiscDataX
import com.example.composecleanarchitecture.models.dashboard.organization_wise.DashOrgWiseCategoryResponse
import com.example.composecleanarchitecture.models.dashboard.previous_year.DashBoardPrevErQuest
import com.example.composecleanarchitecture.models.dashboard.previous_year.OrganizationCategory
import com.example.composecleanarchitecture.models.dashboard.previous_year.PreviousYearQuestion
import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.example.composecleanarchitecture.navigation_controller.Destinations
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.home.components.CarouselPager
import com.example.composecleanarchitecture.ui.features.home.components.CarouselPagerYearWise
import com.example.composecleanarchitecture.ui.features.home.components.CategoryHeadingComponent
import com.example.composecleanarchitecture.ui.features.home.components.CourseModelTestCarouselPager
import com.example.composecleanarchitecture.ui.features.organization_wise_question.components.OrganizeWiseCarousel
import com.example.composecleanarchitecture.ui.features.subject_wise.components.CarouselSubjectCategory
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.BCS_TOMO_LIST_DATA
import com.example.composecleanarchitecture.utils.DASHBOARD_ORG_CATEGORY
import com.example.composecleanarchitecture.utils.DESIGNATION_OF_ORGANIZATION
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_USING_YEAR
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA
import com.example.composecleanarchitecture.utils.TOPICS_OF_SUBJECT_CATEGORY
import com.example.composecleanarchitecture.utils.getYearsArray
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.SharedViewModel
import com.example.composecleanarchitecture.view_models.dashBoard.DashBoardViewModel


@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val loginViewModel: DashBoardViewModel = hiltViewModel()

    val scrollState = rememberScrollState()
    var stateId: Int? by remember { mutableStateOf(null) }
    val previousYearCategory = remember { mutableStateListOf<PreviousYearQuestion>() }
    val organizeWiseCategory = remember { mutableStateListOf<OrganizationCategory>() }
    val pvsErQuestCatImage = listOf(
        R.drawable.ic_bcs_pasc,
        R.drawable.ic_bcs_pasc,
        R.drawable.ic_bank,
        R.drawable.ic_school_college,
        R.drawable.ic_school_college,
        R.drawable.ic_primary_school,
        R.drawable.ic_du_iba,
        R.drawable.ic_judge,
        R.drawable.ic_private_bank
    )
    val courseDataItemList: LazyPagingItems<CourseListWithTopiscDataX> =
        loginViewModel.getAllCourseList()
            .collectAsLazyPagingItems()
    val subjectsDataItemList: LazyPagingItems<SubjectListResponseDataX> =
        loginViewModel.getALlSubjects()
            .collectAsLazyPagingItems()
    val yearList = getYearsArray()
    val context = LocalContext.current
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    val preferencesHelper = SharedPreferencesHelper(context)

    LaunchedEffect(key1 = true) {
        loginViewModel.getPrevErQuestCategory()
        loginViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    stateId = it.stateId
                    isShowProgressBar.value = true
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        101 -> {
                            previousYearCategory.clear()
                            loginViewModel.getDashBoardOrganizationQuestion()
                            val data = it.data as DashBoardPrevErQuest
                            val categoriesList =
                                data.data?.categories?.data?.sortedBy { its -> its.id?.toInt() }
                            categoriesList?.forEachIndexed { index, item ->
                                item.attributes?.let { items ->
                                    previousYearCategory.add(
                                        PreviousYearQuestion(
                                            isTracerEnable = false,
                                            qsnCategory = items.name!!,
                                            imageVector = pvsErQuestCatImage[index],
                                            qsnStatus = "New",
                                            qsnId = items.code!!
                                        )
                                    )
                                }
                            }
                            loginViewModel.savePreviousYearCategories(previousYearCategory)
                        }

                        102 -> {
                            organizeWiseCategory.clear()
                            val data = it.data as DashOrgWiseCategoryResponse
                            val categoriesList = data.data?.organizations?.data
                            categoriesList?.forEachIndexed { _, item ->
                                item.attributes?.let { items ->
                                    organizeWiseCategory.add(
                                        OrganizationCategory(
                                            isTracerEnable = false,
                                            qsnCategory = items.name!!,
                                            imageVector = R.drawable.ic_bank,
                                            qsnStatus = "New",
                                            qsnId = items.code!!
                                        )
                                    )
                                }
                            }
                            if (preferencesHelper[DASHBOARD_ORG_CATEGORY, ""] != "1") {
                                loginViewModel.saveDashBoardOrganizationCategories(
                                    organizeWiseCategory
                                )
                            }
                        }

                        103 -> {
                            val data = it.data as List<PreviousYearQuestion>
                            previousYearCategory.clear()
                            loginViewModel.getDashBoardOrganizationQuestion()
                            previousYearCategory.addAll(data)

                        }

                        104 -> {
                            val data = it.data as List<OrganizationCategory>
                            organizeWiseCategory.clear()
                            organizeWiseCategory.addAll(data)

                        }
                    }
                }

                is UIState.Error -> {
                    isShowProgressBar.value = false
                    context.showToast(it.message)
                }

                else -> {

                }
            }
        }
    }
    BaseComponent(backgroundColor = MaterialTheme.colorScheme.background,
        progressBarState = loginViewModel.showProgressBar.collectAsState(),
        unauthorizedState = loginViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = isShowProgressBar.value, color = Color.Green)
        },
        unAuthorizedContent = {

        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = secondaryColor)
        ) {
            ToolBarLayoutComponent(
                navController = navController,
                isBackShow = false,
                title = "রাফখাতা",
                textSize = 18.sp
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = secondaryColor)
                    .verticalScroll(state = scrollState)
            ) {

                CategoryHeadingComponent("বিগত সালের প্রশ্ন", "${previousYearCategory.size}") {
                    navController.navigate(Destinations.AllPreviousYearCategory.route)

                }
                CarouselPager(categories = previousYearCategory) {
                    when (it) {
                        "1", "8" -> {
                            if ((preferencesHelper["$BCS_TOMO_LIST_DATA${it}", ""] == "1"
                                        && !context.isInternetAvailable()) or context.isInternetAvailable()
                            ) {
                                navController.navigate(route = "SequenceByTomoScreen/${it}")
                            } else {
                                context.showToast("Check your internet connection")
                            }
                        }

                        "3", "4", "5", "6", "9" -> {
                            if ((preferencesHelper["$PREVIOUS_ER_QUES_YEAR_LIST_BANK_EDU_INS${it}", ""] == "1"
                                        && !context.isInternetAvailable()) or context.isInternetAvailable()
                            ) {
                                navController.navigate(route = "BankEduInstCatItemByYear/${it}")
                            } else {
                                context.showToast("Check your internet connection")
                            }
                        }

                        else -> {
                            if ((preferencesHelper["org_name$PREVIOUS_ER_QUES_YEAR_ORG_LIST_PSC_IBA${it}", ""] == "1"
                                        && !context.isInternetAvailable()) or context.isInternetAvailable()
                            ) {
                                navController.navigate("IBAPSCOrganizationsScreen/${it}")
                            } else {
                                context.showToast("Check your internet connection")
                            }

                        }
                    }
                    /*when (it) {
                        "1", "8" -> {
                            navController.navigate(route = "SequenceByTomoScreen/$it")
                        }

                        "3", "4", "5", "6", "9" -> {
                            navController.navigate(route = "BankEduInstCatItemByYear/$it")
                        }

                        else -> {
                            navController.navigate("IBAPSCOrganizationsScreen/$it")
                        }
                    }*/
                }

                CategoryHeadingComponent(
                    "প্রতিষ্ঠান ভিত্তিক প্রশ্ন", "${organizeWiseCategory.size}"
                ) {
                    navController.navigate(Destinations.AllOrganizationListScreen.route)
                }
                OrganizeWiseCarousel(
                    categories = organizeWiseCategory,
                    isImageShow = false,
                    lineHeight = 14.sp,
                    maxLine = 5,
                    height = 110,
                    width = 130
                ) {
                    if ((preferencesHelper["$DESIGNATION_OF_ORGANIZATION${it}", ""] == "1"
                                && !context.isInternetAvailable()) or context.isInternetAvailable()
                    ) {
                        navController.navigate("OrgWiseDesignationListScreen/$it")
                    } else {
                        context.showToast("Check your internet connection")
                    }
                }

                CategoryHeadingComponent("কোর্স", "${courseDataItemList.itemCount}") {
                    navController.navigate(Destinations.AllCoursesScreen.route)
                }
                CourseModelTestCarouselPager(items = courseDataItemList.itemCount) { cItem ->
                    sharedViewModel._courseOutLateData.value = cItem
                    navController.navigate(Destinations.CourseOutletScreen.route)
                }


                CategoryHeadingComponent("সাল ভিত্তিক প্রশ্ন", "${yearList.size}") {
                    navController.navigate(Destinations.AllYearCardListScreen.route)
                }
                CarouselPagerYearWise {
                    if ((preferencesHelper["$PREVIOUS_ER_QUES_USING_YEAR$it", ""] == "1" && !context.isInternetAvailable()) || context.isInternetAvailable()) {
                        navController.navigate("PrevErQuesByYearSplitItemScreen/$it")
                    } else {
                        context.showToast("Check your internet connection.")
                    }

                }

                /* CategoryHeadingComponent(
                     "টপিক ভিত্তিক প্রশ্ন",
                     "${topicsDataItemList.itemCount}"
                 ) {
                     sharedViewModel.selectedCategory.value = "topics_wise"
                     navController.navigate("AllTopicsListScreen/0")
                 }

                 CarouselTopics("topics_wise", topicsDataItemList.itemCount) {
                     sharedViewModel.selectedCategory.value = "topics_wise"
                     navController.navigate("TopicsWiseQuestionListScreen/$it")
                 }*/


                CategoryHeadingComponent(
                    "বিষয় ভিত্তিক প্রশ্ন",
                    "${subjectsDataItemList.itemCount}"
                ) {
                    sharedViewModel.selectedCategory.value = "subject_wise"
                    navController.navigate(Destinations.AllSubjectListScreen.route)
                }
                CarouselSubjectCategory(subjectsDataItemList.itemCount) {
                    if ((preferencesHelper["$TOPICS_OF_SUBJECT_CATEGORY${it}", ""] == "1"
                                && !context.isInternetAvailable()) or context.isInternetAvailable()
                    ) {
                        sharedViewModel.selectedCategory.value = "subject_wise"
                        navController.navigate("AllTopicsListScreen/$it")
                    } else {
                        context.showToast("Check your internet connection")
                    }

                }

            }
        }
    }


}