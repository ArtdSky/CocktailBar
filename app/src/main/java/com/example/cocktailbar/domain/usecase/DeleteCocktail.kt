package com.example.cocktailbar.domain.usecase

import com.example.cocktailbar.domain.repository.CocktailRepository

class DeleteCocktail(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(id: Int) {
        return cocktailRepository.deleteCocktailById(id)
    }

}