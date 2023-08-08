package com.example.cocktailbar.data.storage.local

import com.example.cocktailbar.data.storage.local.models.CocktailEntity

class DatabaseRepository(
    private val cocktailDao: CocktailDao
) {

    suspend fun getAllCocktails(): List<CocktailEntity> {
        return cocktailDao.getAllCocktails()
    }

    suspend fun getCocktail(id : Int) : CocktailEntity{
        return cocktailDao.getCocktailById(id)
    }

    suspend fun saveCocktail(cocktail : CocktailEntity) {
        return cocktailDao.saveCocktail(cocktail)
    }
}