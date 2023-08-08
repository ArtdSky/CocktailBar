package com.example.cocktailbar.data.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.cocktailbar.data.storage.local.models.CocktailEntity
import com.example.cocktailbar.domain.models.Cocktail
import java.io.ByteArrayOutputStream

class CocktailUtils {

    companion object {

        fun mapEntityToCocktail(cocktailEntity: CocktailEntity): Cocktail {
            return Cocktail(
                id = cocktailEntity.id,
                name = cocktailEntity.name,
                img = cocktailEntity.img?.let { byteArrayToBitmap(it) },
                description = cocktailEntity.description,
                recipe = cocktailEntity.recipe,
                ingredients = cocktailEntity.ingredients
            )
        }

        fun mapCocktailToEntity(cocktail: Cocktail): CocktailEntity {
            return CocktailEntity(
                id = cocktail.id,
                name = cocktail.name,
                img = cocktail.img?.let { bitmapToByteArray(it) },
                description = cocktail.description,
                recipe = cocktail.recipe,
                ingredients = cocktail.ingredients
            )
        }

        private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        private fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }

    }
}