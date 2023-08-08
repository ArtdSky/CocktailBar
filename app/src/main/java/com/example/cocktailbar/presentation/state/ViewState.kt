package com.example.cocktailbar.presentation.state

import com.example.cocktailbar.domain.models.Cocktail


data class ViewState(
    val cocktail: Cocktail? = null
)