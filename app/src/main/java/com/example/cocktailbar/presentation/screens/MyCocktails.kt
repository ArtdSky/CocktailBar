package com.example.cocktailbar.presentation.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DiscouragedApi")
@Composable
fun MyCocktails(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {

    val state by vm.viewState.collectAsState()

    val yourBitmapImage1: Bitmap? = null
    val itemsList: List<Cocktail> = listOf(
        Cocktail(
            id = 1,
            name = "Mojito",
            img = yourBitmapImage1,
            description = "Refreshing cocktail with mint and lime",
            recipe = "1. Muddle mint leaves and lime juice\n2. Add rum, sugar, and ice\n3. Shake well and strain into glass\n4. Top with soda water\n5. Garnish with mint sprig",
            ingredients = listOf("2 oz white rum", "1 oz lime juice", "2 tsp sugar", "8-10 fresh mint leaves", "soda water")
        ),
        Cocktail(
            id = 2,
            name = "Cosmopolitan",
            img = yourBitmapImage1,
            description = "Classic vodka cocktail with citrus flavors",
            recipe = "1. Shake all ingredients with ice\n2. Strain into a martini glass\n3. Garnish with lemon twist",
            ingredients = listOf("1.5 oz vodka", "1 oz cranberry juice", "0.5 oz triple sec", "0.5 oz lime juice")
        ),
        Cocktail(
            id = 3,
            name = "Mojito2",
            img = yourBitmapImage1,
            description = "Refreshing cocktail with mint and lime",
            recipe = "1. Muddle mint leaves and lime juice\n2. Add rum, sugar, and ice\n3. Shake well and strain into glass\n4. Top with soda water\n5. Garnish with mint sprig",
            ingredients = listOf("2 oz white rum", "1 oz lime juice", "2 tsp sugar", "8-10 fresh mint leaves", "soda water")
        ),
        Cocktail(
            id = 4,
            name = "Cosmopolitan2",
            img = yourBitmapImage1,
            description = "Classic vodka cocktail with citrus flavors",
            recipe = "1. Shake all ingredients with ice\n2. Strain into a martini glass\n3. Garnish with lemon twist",
            ingredients = listOf("1.5 oz vodka", "1 oz cranberry juice", "0.5 oz triple sec", "0.5 oz lime juice")
        )
    )
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
                    columns = GridCells.Fixed(2)
                ) {
                    items(itemsList.size) { item ->


                        itemsList[item].img?.let { img ->
                            Image(
                                bitmap = img.asImageBitmap(),
                                contentDescription = "image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(350.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(15))
                        ) {
                            Image(
                                painter = painterResource(R.drawable.crayton),
                                contentDescription = "Summer Holidays",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(350.dp)
                                    .clickable { Log.d("TAG",  itemsList[item].id.toString() ) }
                            )
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
                                    color = Color.White
                                ),
                            )
                        }


                    }
                }

                FloatingActionButton(
                    onClick = { },
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