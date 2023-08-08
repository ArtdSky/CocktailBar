package com.example.cocktailbar.domain.models

data class Cocktail(
    val id : Int,
    val name : String,
    val img : Int? = null,
    val description : String? = null,
    val recipe : String? = null,
    val ingridients: String? = null
)
