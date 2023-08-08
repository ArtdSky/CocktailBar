package com.example.cocktailbar.domain.models

import android.graphics.Bitmap
import android.net.Uri

data class Cocktail(
    val id : Int? = null,
    val name : String,
    val img : String? = null,
    val description : String? = null,
    val recipe : String? = null,
    val ingredients: List<String>? = null
)


data class Cocktail2(
    val id : Int,
    val name : String,
    val img : Bitmap? = null,
    val description : String? = null,
    val recipe : String? = null,
    val ingredients: List<String>? = null
)