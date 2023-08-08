package com.example.cocktailbar.domain.usecase

import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.repository.CocktailRepository

class UpdateCocktail(
    private val cocktailRepository: CocktailRepository
) {

    suspend operator fun invoke(
        id: Int,
        name: String,
        img: String?,
        description: String?,
        recipe: String?,
        ingredients: List<String>?
    ) {
        return cocktailRepository.updateCocktailById(
            id = id,
            name = name,
            img = img,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }

}