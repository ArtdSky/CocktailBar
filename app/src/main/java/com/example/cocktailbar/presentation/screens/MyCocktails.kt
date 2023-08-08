package com.example.cocktailbar.presentation.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import coil.compose.rememberAsyncImagePainter
import com.example.cocktailbar.R
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DiscouragedApi")
@Composable
fun MyCocktails(
    vm: MainViewModel,
    navController: NavHostController,
) {
    val TAG = "MyCocktails"
    val state by vm.viewState.collectAsState()
    Log.d(TAG, state.toString())
    vm.getAllCocktailsOutDb()
    val itemsList: List<Cocktail> = state.cocktails ?: emptyList()
    Scaffold(

        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

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

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),

                ) {
                    items(itemsList.size) { item ->
                        Box(
                            modifier = Modifier
                                .size(350.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(15))
                        ) {
                            itemsList[item].img?.let { img ->
                                val uriImg = Uri.parse(img)
                                Image(
                                    painter = rememberAsyncImagePainter(model = uriImg),
                                    contentDescription = "cocktail",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = RoundedCornerShape(18.dp))
                                        .clickable {
                                            itemsList[item].id?.let { vm.setCocktailId(it) }
                                            navController.navigate(Screen.CocktailDetails.route)
                                        }
                                )
                            }

                            Text(
                                text = itemsList[item].name,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.BottomCenter),
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 23.sp,
                                    letterSpacing = 0.sp,
                                    textAlign = TextAlign.Right,
                                    color = Color.Black
                                ),
                            )
                        }


                    }
                }

                FloatingActionButton(
                    onClick = {
                        vm.clearCocktailId()
                        navController.navigate(Screen.AddCocktail.route)
                    },
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
    )

}