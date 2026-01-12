package com.example.composecleanarchitecture.ui.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecleanarchitecture.R
import com.example.composecleanarchitecture.navigation_controller.Destinations
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.nostoBanglaFont
import com.example.composecleanarchitecture.ui.theme.whiteColor
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = Unit) {
        delay(500)
        navController.navigate(Destinations.LoginScreen.route){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteColor)
            .paint(
                painterResource(id = R.drawable.raf_khata_splash_background),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.ic_raf_khata), contentDescription = null,
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "রাফখাতা",
            color = activeTextColor,
            fontFamily = nostoBanglaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            letterSpacing = 0.03.sp
        )
    }

}

@Preview
@Composable
fun previewOpt() {
    SplashScreen(rememberNavController())
}