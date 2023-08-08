package com.example.cocktailbar.data.storage

import com.example.cocktailbar.data.storage.local.models.CocktailEntity

interface CocktailsLocalStorage {

    suspend fun getAllCocktails(): List<CocktailEntity>

    suspend fun saveCocktail(cocktail: CocktailEntity)

    suspend fun getCocktailById(id: Int) : CocktailEntity
}