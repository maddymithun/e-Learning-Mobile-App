package com.example.composecleanarchitecture.ui.features.previousyear_question

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composecleanarchitecture.base.BaseComponent
import com.example.composecleanarchitecture.base.UIState
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import com.example.composecleanarchitecture.models.tomo_sequence.Attributes
import com.example.composecleanarchitecture.models.tomo_sequence.BCSTomoDataFormation
import com.example.composecleanarchitecture.models.tomo_sequence.DataX
import com.example.composecleanarchitecture.ui.common_components.ProgressBarHandler
import com.example.composecleanarchitecture.ui.common_components.ToolBarLayoutComponent
import com.example.composecleanarchitecture.ui.features.previousyear_question.components.CardTomoSequence
import com.example.composecleanarchitecture.ui.theme.secondaryColor
import com.example.composecleanarchitecture.utils.BCS_TOMO_LIST_DATA
import com.example.composecleanarchitecture.utils.PREVIOUS_ER_QUES_LIST_DETAILS
import com.example.composecleanarchitecture.utils.isInternetAvailable
import com.example.composecleanarchitecture.utils.showToast
import com.example.composecleanarchitecture.view_models.previousyear_question.PreviousYearQuestionViewModel
import java.time.Instant
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SequenceByTomoScreen(navController: NavController, categoryCode: String) {
    val previousErQusViewModel: PreviousYearQuestionViewModel = hiltViewModel()
    val scrollState = rememberLazyGridState()
    var stateId: Int? by remember { mutableStateOf(null) }
    val sequenceSortValue = remember { mutableStateListOf<BCSTomoDataFormation>() }
    val context = LocalContext.current
    val preferencesHelper = SharedPreferencesHelper(context)
    val isShowProgressBar = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        previousErQusViewModel.getCategoryTomoSequence(categoryCode = categoryCode)
        previousErQusViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {
                    stateId = it.stateId
                    isShowProgressBar.value = true
                }

                is UIState.DataLoaded -> {
                    isShowProgressBar.value = false
                    when (it.stateId) {
                        103 -> {
                            val data = it.data as ArrayList<DataX>
                            val sequenceData =
                                (data.distinctBy { its -> its.attributes?.sequence } as MutableList)
                            val dataSeries = ArrayList<DataX>()
                            sequenceData.forEach { item ->

                                if (item.attributes?.sequence == "") {
                                    val timestamp = Instant.now().toEpochMilli()
                                    val randomInt =
                                        Random.nextInt(10000, 20000)
                                    val unique = timestamp - randomInt
                                    val arrtibutes = Attributes(
                                        sequence = randomInt.toString(),
                                        year = item.attributes.year
                                    )
                                    dataSeries.add(DataX(attributes = arrtibutes, id = item.id))
                                } else {
                                    dataSeries.add(item)
                                }
                            }
                            val sortedData =
                                dataSeries.sortedBy { its -> its.attributes?.sequence?.toInt() }
                            //   sequenceSortValue.addAll(sortedData)
                            sortedData.forEach { item ->
                                sequenceSortValue.add(
                                    BCSTomoDataFormation(
                                        sequence = item.attributes?.sequence!!,
                                        categoryCode = categoryCode,
                                        bcsYear = item.attributes.year.toString()
                                    )
                                )
                            }
                            if (preferencesHelper["$BCS_TOMO_LIST_DATA$categoryCode", ""] != "1") {
                                previousErQusViewModel.insertBcsTomo(
                                    categoryCode,
                                    sequenceSortValue
                                )
                            }
                        }

                        104 -> {
                            val data = it.data as List<BCSTomoDataFormation>
                            sequenceSortValue.clear()
                            sequenceSortValue.addAll(data)
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
    BaseComponent(
        backgroundColor = secondaryColor,
        progressBarState = previousErQusViewModel.showProgressBar.collectAsState(),
        unauthorizedState = previousErQusViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            when (stateId) {
                103 -> {
                    ProgressBarHandler(show = isShowProgressBar.value, color = Color.Green)
                }
            }
        },
        unAuthorizedContent = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = secondaryColor)
        ) {
            ToolBarLayoutComponent(
                navController = navController,
                isBackShow = true,
                title = "বিগত সালের প্রশ্ন",
                textSize = 16.sp
            )

            /*  Spacer(modifier = Modifier.height(20.dp))
              Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                  CustomTextComponents(
                      title = "বিগত সালের প্রশ্ন",
                      fontWeight = FontWeight.W400,
                      textColor = blackVarColor1,
                      textSize = 16.sp
                  )
              }*/

            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(sequenceSortValue.size) { index ->
                    CardTomoSequence(tomoData = sequenceSortValue[index]) { data ->
                        if (((preferencesHelper["$PREVIOUS_ER_QUES_LIST_DETAILS$categoryCode${data.sequence}${data.bcsYear}", ""] == "1") && !context.isInternetAvailable()) || context.isInternetAvailable()) {
                            navController.navigate(
                                route =
                                "BcsPrevYerQuesDetailsScreen/${data.sequence}/${categoryCode}/${data.bcsYear}"
                            )
                        } else {
                            context.showToast("check your internet connection.")
                        }
                    }
                }
            }
        }
    }

}