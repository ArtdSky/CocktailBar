package com.example.cocktailbar.domain.models

import android.graphics.Bitmap

data class Cocktail(
    val id : Int,
    val name : String,
    val img : Bitmap? = null,
    val description : String? = null,
    val recipe : String? = null,
    val ingredients: String? = null
)
