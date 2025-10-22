package com.nicolascommandeur.wikicat.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nicolascommandeur.wikicat.data.local.entities.CatImageEntity
import com.nicolascommandeur.wikicat.data.local.entities.CatWeightEntity

class DatabaseConverters {
    private val gson = Gson()

    // CatWeightEntity to JSON
    @TypeConverter
    fun fromCatWeightEntity(catWeight: CatWeightEntity): String = gson.toJson(catWeight)

    // JSON to CatWeightEntity
    @TypeConverter
    fun toCatWeightEntity(json: String): CatWeightEntity =
        gson.fromJson(json, CatWeightEntity::class.java)

    // CatImageEntity to JSON
    @TypeConverter
    fun fromCatImageEntity(catImage: CatImageEntity): String = gson.toJson(catImage)

    // JSON to CatImageEntity
    @TypeConverter
    fun toCatImageEntity(json: String): CatImageEntity =
        gson.fromJson(json, CatImageEntity::class.java)

}