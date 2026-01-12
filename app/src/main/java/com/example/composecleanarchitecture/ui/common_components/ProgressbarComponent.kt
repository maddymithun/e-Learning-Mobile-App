package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.composecleanarchitecture.ui.theme.whiteColor


@Composable
fun ProgressBarHandler(show: Boolean, color: Color = MaterialTheme.colorScheme.primary) {
    if (!show) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.Center),
            color = color
        )
    }
}

@Composable
fun ListProgressBar() {

        CircularProgressIndicator(
            modifier =
            Modifier
                .testTag("ProgressBarItem")
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(
                    Alignment.CenterHorizontally
                )
        )


}