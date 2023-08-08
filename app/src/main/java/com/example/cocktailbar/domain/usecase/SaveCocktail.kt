package com.example.cocktailbar.domain.usecase

import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository

class SaveCocktail(
    private val cocktailRepository : CocktailRepository
)  {

    suspend operator fun invoke(cocktail: Cocktail): Boolean {
        return cocktailRepository.saveCocktail(cocktail)
    }

}