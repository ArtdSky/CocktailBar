package com.example.cocktailbar.data.storage.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "cocktails_table")
@TypeConverters(ListConverter::class)
data class CocktailEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val img: String? = null,
    val description: String? = null,
    val recipe: String? = null,
    val ingredients: List<String>? = null,
    )


class ListConverter {

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toList(string: String?): List<String>? {
        return string?.split(",")?.map { it.trim() }
    }
}


