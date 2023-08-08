package com.example.cocktailbar.presentation.routing


sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object MyCocktails : Screen("myCocktails_screen")
    object CocktailDetails : Screen("cocktailDetails_screen")
    object AddCocktail : Screen("addCocktail_screen")

}

val AppTabRowScreens = listOf(
    Screen.Main,
    Screen.MyCocktails,
    Screen.CocktailDetails,
    Screen.AddCocktail
)