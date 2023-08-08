package com.example.cocktailbar.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.cocktailbar.R
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel


@Composable
fun CocktailDetails(
    vm: MainViewModel,
    navController: NavHostController,
) {
    val state by vm.viewState.collectAsState()
    state.id?.let { vm.getCocktailOutDb(it) }
    val cocktail: Cocktail = state.cocktail ?: Cocktail(
        name = "Unknown",
    )
    val showDialog = remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                .height(500.dp)
                    .weight(0.7f)
            ) {
                Image(
                    painter = painterResource(R.drawable.crayton),
                    contentDescription = "Cocktail Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
//                .height(300.dp)
                    .offset(y = (-80).dp)
                    .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = cocktail.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                cocktail.description?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 19.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                cocktail.ingredients?.forEach { ingredient ->
                    Text(
                        text = ingredient,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 19.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                cocktail.recipe?.let {
                    Text(
                        text = "Recipe: \n $it",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 19.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Button(
                onClick = { showDialog.value = true },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4B97FF),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .clip(RoundedCornerShape(80.dp))
            ) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    text = {
                        Text("Вы уверены, что хотите удалить коктейль ${cocktail.name}?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog.value = false
                                vm.clearState()
                                cocktail.id?.let {
                                    vm.deleteCocktailInDb(it)
                                }
                                navController.navigate(Screen.Main.route)

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xff4B97FF),
                                contentColor = Color(0xffFFFFFF),
                            ),
                            shape = RoundedCornerShape(80.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                        ) {
                            Text(
                                text = "Да",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W400,
                                lineHeight = 31.sp,
                                textAlign = TextAlign.Center,
                                color = Color(0xffFFFFFF)
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog.value = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color(0xff4B97FF),
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(2.dp, Color(0xff4B97FF)),
                                    shape = RoundedCornerShape(80.dp),
                                )
                                .height(60.dp),
                        ) {
                            Text(
                                text = "Отмена",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W400,
                                lineHeight = 31.sp,
                                textAlign = TextAlign.Center,
                                color = Color(0xff4B97FF),
                            )
                        }
                    }
                )
            }

            FloatingActionButton(
                containerColor = Color(0xFF4B97FF),
                onClick = {
                    navController.navigate(Screen.AddCocktail.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .clip(RoundedCornerShape(80.dp))
            ) {
                Text(
                    text = "Edit",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 31.sp,
                        color = Color.White
                    )
                )
            }
        }

    }
}
