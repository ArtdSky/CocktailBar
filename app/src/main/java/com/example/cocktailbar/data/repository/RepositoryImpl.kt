package com.example.cocktailbar.data.repository

import com.example.cocktailbar.data.storage.CocktailsLocalStorage
import com.example.cocktailbar.data.utils.CocktailUtils
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository
import java.lang.Exception

class RepositoryImpl(
    private val cocktailsLocalStorage: CocktailsLocalStorage
) : CocktailRepository {

    override suspend fun getAllCocktails(): List<Cocktail> {
        val data = cocktailsLocalStorage.getAllCocktails()
        return data.map {
            CocktailUtils.mapEntityToCocktail(it)
        }
    }

    override suspend fun getCocktail(id: Int): Cocktail {
        val data = cocktailsLocalStorage.getCocktailById(id)
        return CocktailUtils.mapEntityToCocktail(data)
    }

    override suspend fun saveCocktail(cocktail: Cocktail): Boolean {
        val entity = CocktailUtils.mapCocktailToEntity(cocktail)
        return try {
            cocktailsLocalStorage.saveCocktail(entity)
            true
        } catch (e: Exception) {
            false
        }
    }
}