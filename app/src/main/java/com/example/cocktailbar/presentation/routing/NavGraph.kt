package com.example.cocktailbar.presentation.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.presentation.viewmodel.MainViewModel
import com.example.cocktailbar.presentation.screens.AddCocktail
import com.example.cocktailbar.presentation.screens.CocktailDetails
import com.example.cocktailbar.presentation.screens.MainScreen
import com.example.cocktailbar.presentation.screens.MyCocktails


@Composable
fun NavGraph(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen
            )
        }

        composable(route = Screen.MyCocktails.route) {
            MyCocktails(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen
            )
        }

        composable(route = Screen.CocktailDetails.route) {
            CocktailDetails(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen,
                cocktail = Cocktail(1,"test")
            )
        }

        composable(
            route = Screen.AddCocktail.route
        ) {
            AddCocktail(
                vm = vm,
                navController = navController,
                currentScreen = currentScreen
            )
        }
    }

}