package com.example.cocktailbar.data.storage.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails_table")
data class CocktailEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val img: ByteArray? = null,
    val description: String? = null,
    val recipe: String? = null,
    val ingredients: String? = null,
    )



