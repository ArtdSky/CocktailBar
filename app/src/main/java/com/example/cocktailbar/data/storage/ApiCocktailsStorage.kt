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

    override suspend fun deleteCocktailById(id: Int) {
        return databaseRepository.deleteCocktailById(id)
    }

    override suspend fun updateCocktailById(
        id: Int,
        name: String,
        img: String?,
        description: String?,
        recipe: String?,
        ingredients: List<String>?
    ) {
        return databaseRepository.updateCocktailById(
            id = id,
            name = name,
            img = img,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }

    override suspend fun getCocktailById(id: Int) : CocktailEntity {
        return databaseRepository.getCocktail(id)
    }
}