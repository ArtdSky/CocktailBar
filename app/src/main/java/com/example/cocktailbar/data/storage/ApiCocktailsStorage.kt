package com.example.cocktailbar.data.storage

import com.example.cocktailbar.data.storage.local.DatabaseRepository
import com.example.cocktailbar.data.storage.local.models.CocktailEntity


class ApiCocktailsStorage(
    private val databaseRepository: DatabaseRepository
) : CocktailsLocalStorage {

    override suspend fun getAllCocktails(): List<CocktailEntity> {
        return databaseRepository.getAllCocktails()
    }

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        return databaseRepository.saveCocktail(cocktail)
    }

    override suspend fun getCocktailById(id: Int) : CocktailEntity {
        return databaseRepository.getCocktail(id)
    }
}