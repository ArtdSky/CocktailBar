package com.example.cocktailbar.data.storage.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailbar.data.storage.local.models.CocktailEntity

interface CocktailDao {

    @Query("SELECT * FROM cocktails_table")
    suspend fun getAllCocktails(): List<CocktailEntity>


    @Query("SELECT * FROM cocktails_table WHERE id = :id")
    suspend fun getCocktailById(id: Int): CocktailEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCocktail(cocktail: CocktailEntity)


}