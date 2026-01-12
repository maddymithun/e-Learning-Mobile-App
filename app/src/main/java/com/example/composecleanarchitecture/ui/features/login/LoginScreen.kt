package com.example.composecleanarchitecture.ui.features.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
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
import com.example.composecleanarchitecture.ui.features.splash.SplashScreen
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.carosoulBgColor
import com.example.composecleanarchitecture.ui.theme.nostoBanglaFont
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun LoginScreen(navController: NavController){

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
    )   {
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
        Spacer(modifier = Modifier.height(70.dp))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(Destinations.DashBoardScreen.route) {
                    popUpTo(navController.graph.startDestinationId){
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 4.dp,
                focusedElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .padding(start = 50.dp, end = 50.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = activeTextColor,
                contentColor = Color.White
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "Continue with Google")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.popBackStack()
                navController.navigate(Destinations.DashBoardScreen.route) {
                    popUpTo(navController.graph.startDestinationId){
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 4.dp,
                focusedElevation = 4.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .padding(start = 50.dp, end = 50.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = carosoulBgColor,
                contentColor = activeTextColor
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook_logo),
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "Continue with Facebook")
        }
    }

}

@Preview
@Composable
fun previewOpt2() {
    LoginScreen(rememberNavController())
}