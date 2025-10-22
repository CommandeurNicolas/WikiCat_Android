package com.nicolascommandeur.wikicat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localVersion")
data class TheCatApiVersionEntity(
    @PrimaryKey val id: Int,
    val version: String
)