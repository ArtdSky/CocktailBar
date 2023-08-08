package com.example.cocktailbar.presentation.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel


@Composable
fun MainScreen(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    val state by vm.viewState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Log.d("MainScreen", state.toString())


}

