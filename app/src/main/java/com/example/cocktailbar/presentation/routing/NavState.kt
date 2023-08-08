package com.example.cocktailbar.presentation.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cocktailbar.presentation.viewmodel.MainViewModel


@Composable
fun NavState(
    vm : MainViewModel
) {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = AppTabRowScreens.find {
        it.route == currentDestination?.route
    } ?: Screen.Main

    NavGraph(
        vm = vm,
        navController = navController,
    )

}