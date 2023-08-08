package com.example.cocktailbar.data.storage.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailbar.data.storage.local.models.CocktailEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails_table")
    suspend fun getAllCocktails(): List<CocktailEntity>


    @Query("SELECT * FROM cocktails_table WHERE id = :id")
    suspend fun getCocktailById(id: Int): CocktailEntity


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCocktail(cocktail: CocktailEntity)

    @Query("DELETE FROM cocktails_table WHERE id = :id")
    suspend fun deleteCocktailById(id: Int)

    @Query("UPDATE cocktails_table SET name = :name, img = :img, description = :description, recipe = :recipe, ingredients = :ingredients WHERE id = :id")
    suspend fun updateCocktailById(id: Int, name: String, img: String?, description: String?, recipe: String?, ingredients: List<String>?)
}