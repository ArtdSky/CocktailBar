package com.example.cocktailbar.domain.usecase

import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository

class GetCocktail(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(id: Int): Cocktail {
        return cocktailRepository.getCocktail(id)
    }

}