package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun Int.LoadLottie(repeat: Int = LottieConstants.IterateForever) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(this))

    LottieAnimation(
        composition = composition,
        iterations = repeat,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
fun fadeInAnimation(startAnimation: Boolean, timeout: Int): State<Float> {
    return animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = timeout
        )
    )
}
