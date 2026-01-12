package com.example.composecleanarchitecture.navigation_controller



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navController: NavController,
    logout: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
        ) {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            BottomBarNavGraph(navController = navController){
                logout()
            }
        }

    }

}

