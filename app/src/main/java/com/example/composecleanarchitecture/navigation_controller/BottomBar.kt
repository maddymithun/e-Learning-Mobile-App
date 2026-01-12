package com.example.composecleanarchitecture.navigation_controller

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composecleanarchitecture.ui.theme.activeTextColor
import com.example.composecleanarchitecture.ui.theme.blackVarColor1
import com.example.composecleanarchitecture.ui.theme.unselectedBottomBarColor
import com.example.composecleanarchitecture.ui.theme.whiteColor

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomBarDestination.HomeScreen,
        BottomBarDestination.QuestionBankScreen,
        BottomBarDestination.MyQuestionsScreen,
        BottomBarDestination.CircularScreen,
    )

    NavigationBar(
        tonalElevation = 10.dp,
        containerColor = whiteColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.map {
            val selected = currentRoute == it.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = it.icon),
                        contentDescription = it.title,
                        tint = if (selected) activeTextColor else blackVarColor1
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },

                selected = currentRoute == it.route,
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = unselectedBottomBarColor,
                    selectedTextColor = activeTextColor
                ),
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },

                )
        }

    }
}