package com.example.cocktailbar.data.storage

import com.example.cocktailbar.data.storage.local.models.CocktailEntity

interface CocktailsLocalStorage {

    suspend fun getAllCocktails(): List<CocktailEntity>

    suspend fun saveCocktail(cocktail: CocktailEntity)

    suspend fun getCocktailById(id: Int) : CocktailEntity

    suspend fun deleteCocktailById(id: Int)

    suspend fun updateCocktailById(
        id: Int,
        name: String,
        img: String?,
        description: String?,
        recipe: String?,
        ingredients: List<String>?
    )
}