package com.example.cocktailbar.presentation.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.cocktailbar.domain.models.Cocktail2
import com.example.cocktailbar.presentation.routing.Screen
import com.example.cocktailbar.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCocktail(
    vm: MainViewModel,
    navController: NavHostController,
    currentScreen: Screen
) {
    val state by vm.viewState.collectAsState()

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var recipe by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var ingredients = remember { mutableStateListOf<String>() }
    var newIngredient by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    imageUri = it
                }
            }
        }

    fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
        launcher.launch(intent)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 73.dp)
                        .height(254.dp)
                        .clip(RoundedCornerShape(17))
                        .clickable { pickImage() }
                )
            } else {
                IconButton(
                    onClick = { pickImage() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xffEEEEEE),
                        contentColor = Color(0xff79747E)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 73.dp)
                        .height(254.dp)
                        .clip(RoundedCornerShape(17)),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = "Camera",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Title") },
                singleLine = true,
                placeholder = { Text("Cocktail name") },
                supportingText = { Text("Add title") },
                shape = RoundedCornerShape(14),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF0000),
                    unfocusedBorderColor = Color(0xFFD0C4C4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text("Description")
                },
                singleLine = true,
                placeholder = {
                    Text("To make this home made")
                },
                supportingText = { Text("Optional field") },
                shape = RoundedCornerShape(14),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF0000),
                    unfocusedBorderColor = Color(0xFFD0C4C4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .height(40.dp)
                ) {


                    ingredients.forEach { ingredient ->
                        Button(
                            onClick = { ingredients.remove(ingredient) },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFFFFF),
                                contentColor = Color(0xFF313131)
                            ),
                            modifier = Modifier
                                .height(40.dp)
                                .padding(start = 8.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,

                                ) {
                                Row {
                                    Text(text = ingredient)
                                    Icon(Icons.Default.Close, contentDescription = "close")
                                }
                            }
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
                            .padding(start = 8.dp)
                            .size(40.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "add")
                    }
                    if (showDialog.value) {
                        AlertDialog(
                            onDismissRequest = { showDialog.value = false },
                            title = { Text(text = "Add ingredient") },
                            text = {
                                OutlinedTextField(
                                    value = newIngredient,
                                    onValueChange = { newIngredient = it },
                                    label = {
                                        Text("Ingredient")
                                    },
                                    singleLine = true,
                                    placeholder = {
                                        Text("Ingredient's name")
                                    }
                                )
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        ingredients.add(newIngredient)
                                        showDialog.value = false
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
                                        text = "Add",
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
                                        text = "Cancel",
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
                }

                Spacer(modifier = Modifier.height(24.dp))


                OutlinedTextField(
                    value = recipe,
                    onValueChange = { recipe = it },
                    label = {
                        Text("Recipe")
                    },
                    singleLine = true,
                    placeholder = {
                        Text("Simply combine all the ingredients")
                    },
                    supportingText = { Text("Optional field") },
                    shape = RoundedCornerShape(14),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF0000),
                        unfocusedBorderColor = Color(0xFFD0C4C4)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(horizontal = 16.dp)
                )


                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (name.isNotEmpty() && imageUri != null && description.isNotEmpty() && recipe.isNotEmpty() && ingredients.isNotEmpty()) {
                            vm.addCocktailToDb(
                                Cocktail(
                                    name = name,
                                    img = imageUri.toString(),
                                    description = description,
                                    recipe = recipe,
                                    ingredients = ingredients
                                )
                            )
                        }
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
                        text = "Save",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 31.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                        color = Color(0xffFFFFFF)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                Button(
                    onClick = { navController.navigate(Screen.MyCocktails.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xff4B97FF)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(2.dp, Color(0xff4B97FF)),
                            shape = RoundedCornerShape(80.dp)
                        )
                        .height(60.dp),
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 31.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.didactgothicregular)),
                        color = Color(0xff4B97FF),

                        )
                }


            }
        }
    }
}