package com.example.cocktailbar.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailbar.domain.models.Cocktail
import com.example.cocktailbar.domain.usecase.GetAllCocktails
import com.example.cocktailbar.domain.usecase.GetCocktail
import com.example.cocktailbar.domain.usecase.SaveCocktail
import com.example.cocktailbar.presentation.state.ViewState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllCocktails: GetAllCocktails,
    private val getCocktail: GetCocktail,
    private val saveCocktail: SaveCocktail
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()

    fun addCocktailToDb(cocktail: Cocktail) {
        viewModelScope.launch {
            saveCocktail(cocktail)
            Log.d("VM-addCocktailToDb", cocktail.toString())
        }
    }

    fun getAllCocktailOutDb() {
        viewModelScope.launch {
            val cocktails = getAllCocktails()
            _viewState.update { currentState ->
                currentState.copy(
                    cocktails = cocktails,
                )
            }
            Log.d("VM-getAllCocktailOutDb", cocktails.toString())
        }
    }

    fun getCocktailOutDb() {
        viewModelScope.launch {
            val cocktail = getAllCocktails()
            _viewState.update { currentState ->
                currentState.copy(
                    cocktails = cocktail,
                )
            }
            Log.d("VM-getAllCocktailOutDb", cocktail.toString())
        }
    }


}