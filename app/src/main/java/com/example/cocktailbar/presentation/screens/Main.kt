package com.example.cocktailbar.presentation.screens

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cocktailbar.R
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    vm: MainViewModel,
    navController: NavHostController,
) {

    val state by vm.viewState.collectAsState()


    if(state.cocktails == null || state.cocktails!!.isEmpty()){
        WelcomeScreen(vm = vm, navController = navController)
    } else {
        MyCocktails(vm = vm, navController = navController)
    }





}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    vm: MainViewModel,
    navController: NavHostController,
) {
    Scaffold(
        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.summer_holidays_1),
                    contentDescription = "Summer Holidays",
                    modifier = Modifier.size(350.dp)
                )
                Text(
                    text = "My Cocktails",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 47.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .size(width = 150.dp, height = 250.dp)
                ) {
                    Column {
                        Text(text = "Add your first")
                        Text(text = "cocktail here")
                    }
                    Icon(
                        painter = painterResource(R.drawable.arrow_down),
                        contentDescription = null
                    )
                    FloatingActionButton(
                        onClick = { navController.navigate(Screen.AddCocktail.route)},
                        content = {
                            Icon(Icons.Default.Add, contentDescription = "add")
                        },
                        shape = RoundedCornerShape(50),
                        containerColor = Color(0xFF4B97FF),
                        contentColor = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .size(80.dp)
                    )
                }

            }
        }
    )
}