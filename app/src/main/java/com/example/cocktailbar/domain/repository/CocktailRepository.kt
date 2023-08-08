package com.example.cocktailbar.domain.repository

import com.example.cocktailbar.domain.models.Cocktail

interface CocktailRepository {

    suspend fun getAllCocktails(): List<Cocktail>

    suspend fun getCocktail(id: Int): Cocktail

    suspend fun saveCocktail(cocktail: Cocktail): Boolean

}