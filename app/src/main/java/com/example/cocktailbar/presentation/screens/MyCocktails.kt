package com.example.cocktailbar.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel
import java.util.*


@SuppressLint("DiscouragedApi")
@Composable
fun MyCocktails(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    val state by vm.viewState.collectAsState()

}