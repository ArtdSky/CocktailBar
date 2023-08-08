package com.example.cocktailbar.data.storage

import com.example.cocktailbar.data.storage.local.AppRepository
import com.example.cocktailbar.data.storage.local.models.CocktailEntity


class ApiCocktailsStorage(
    private val appRepository: AppRepository
) : CocktailsLocalStorage {

    override suspend fun getAllCocktails(): List<CocktailEntity> {
        return appRepository.getAllCocktails()
    }

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        return appRepository.saveCocktail(cocktail)
    }

    override suspend fun getCocktailById(id: Int) : CocktailEntity {
        return appRepository.getCocktail(id)
    }
}