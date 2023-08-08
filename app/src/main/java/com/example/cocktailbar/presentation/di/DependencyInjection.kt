package com.example.cocktailbar.presentation.di

import com.example.cocktailbar.data.repository.RepositoryImpl
import com.example.cocktailbar.data.storage.ApiCocktailsStorage
import com.example.cocktailbar.data.storage.CocktailsLocalStorage
import com.example.cocktailbar.data.storage.local.AppDatabase
import com.example.cocktailbar.data.storage.local.DatabaseRepository
import com.example.cocktailbar.domain.repository.CocktailRepository
import com.example.cocktailbar.domain.usecase.DeleteCocktail
import com.example.cocktailbar.domain.usecase.GetAllCocktails
import com.example.cocktailbar.domain.usecase.GetCocktail
import com.example.cocktailbar.domain.usecase.SaveCocktail
import com.example.cocktailbar.domain.usecase.UpdateCocktail
import com.example.cocktailbar.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val DependencyInjection = module {

    //Data
    single {
        AppDatabase.getDatabase(androidContext())
    }
    single { get<AppDatabase>().cocktailDao() }
    single { DatabaseRepository(get()) }

    single<CocktailsLocalStorage> { ApiCocktailsStorage(get()) }

    single<CocktailRepository> { RepositoryImpl(get()) }

    //Usecases
    factory { SaveCocktail(get()) }
    factory { GetCocktail(get()) }
    factory { GetAllCocktails(get()) }
    factory { DeleteCocktail(get()) }
    factory { UpdateCocktail(get()) }


    // View Model
    viewModel {
        MainViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }

}