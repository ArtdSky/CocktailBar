package com.example.cocktailbar.domain.usecase

import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository

class GetAllCocktails(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(): List<Cocktail> {
        return cocktailRepository.getAllCocktails()
    }

}