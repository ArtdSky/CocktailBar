package com.example.cocktailbar.data.repository

import com.example.cocktailbar.data.storage.CocktailsLocalStorage
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository

class RepositoryImpl(
    private val cocktailsLocalStorage: CocktailsLocalStorage
) : CocktailRepository {

    override suspend fun getAllCocktails(): List<Cocktail> {
        TODO("Not yet implemented")
    }

    override suspend fun getCocktail(id: Int): Cocktail {
        TODO("Not yet implemented")
    }

    override suspend fun saveCocktail(cocktail: Cocktail): Boolean {
        TODO("Not yet implemented")
    }
}