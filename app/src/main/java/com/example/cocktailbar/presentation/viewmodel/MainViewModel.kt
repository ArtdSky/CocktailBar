package com.example.cocktailbar.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cocktailbar.domain.usecase.GetAllCocktails
import com.example.cocktailbar.domain.usecase.GetCocktail
import com.example.cocktailbar.domain.usecase.SaveCocktail
import com.example.cocktailbar.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val getAllCocktails: GetAllCocktails,
    private val getCocktail: GetCocktail,
    private val saveCocktail: SaveCocktail
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()

    private val TAG = "VM"

    init {
        Log.d(TAG, "test dependency")
    }
    

}