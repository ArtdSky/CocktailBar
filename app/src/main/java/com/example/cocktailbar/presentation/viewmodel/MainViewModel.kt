package com.example.cocktailbar.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.usecase.DeleteCocktail
import com.example.cocktailbar.domain.usecase.GetAllCocktails
import com.example.cocktailbar.domain.usecase.GetCocktail
import com.example.cocktailbar.domain.usecase.SaveCocktail
import com.example.cocktailbar.domain.usecase.UpdateCocktail
import com.example.cocktailbar.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllCocktails: GetAllCocktails,
    private val getCocktail: GetCocktail,
    private val saveCocktail: SaveCocktail,
    private val deleteCocktail: DeleteCocktail,
    private val updateCocktail: UpdateCocktail,
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()

    init {
        getAllCocktailsOutDb()
    }

    fun addCocktailToDb(cocktail: Cocktail) {
        viewModelScope.launch {
            saveCocktail(cocktail)
        }
    }

    fun getAllCocktailsOutDb() {
        viewModelScope.launch {
            val cocktails = getAllCocktails()
            _viewState.update { currentState ->
                currentState.copy(
                    cocktails = cocktails,
                )
            }
        }
    }

    fun getCocktailOutDb(id: Int) {
        viewModelScope.launch {
            val cocktail = getCocktail(id)
            _viewState.update { currentState ->
                currentState.copy(
                    cocktail = cocktail,
                )
            }
        }
    }

    fun setCocktailId(id: Int) {
        viewModelScope.launch {
            _viewState.update { currentState ->
                currentState.copy(
                    id = id,
                )
            }
            Log.d("TAG", id.toString())
        }
    }

    fun clearCocktailId() {
        viewModelScope.launch {
            _viewState.update { currentState ->
                currentState.copy(
                    id = null,
                )
            }
        }
    }

    fun clearState() {
        viewModelScope.launch {
            _viewState.update { currentState ->
                currentState.copy(
                    id = null,
                    cocktail = null,
                    cocktails = null
                )
            }
        }
    }

    fun deleteCocktailInDb(id: Int) {
        viewModelScope.launch {
            deleteCocktail(id)
        }
    }

    fun updateCocktailInDb(
        id: Int,
        name: String,
        img: String?,
        description: String?,
        recipe: String?,
        ingredients: List<String>?
    ) {
        viewModelScope.launch {
            updateCocktail(
                id = id,
                name = name,
                img = img,
                description = description,
                recipe = recipe,
                ingredients = ingredients
            )
        }
    }

}