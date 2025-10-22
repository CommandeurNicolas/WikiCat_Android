package com.nicolascommandeur.wikicat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catBreed")
data class CatBreedEntity(
    @PrimaryKey val id: String,
    val name: String,
    val weight: CatWeightEntity,
    val cfaUrl: String?,
    val vetStreetUrl: String?,
    val vcaHospitalsUrl: String?,
    val wikipediaUrl: String?,
    val temperament: String?,
    val origin: String,
    val countryCode: String,
    val description: String,
    val lifeSpan: String?,
    val altNames: String?,
    val experimental: Int?,
    val hairless: Int?,
    val natural: Int?,
    val rare: Int?,
    val rex: Int?,
    val suppressedTail: Int?,
    val shortLegs: Int?,
    val hypoallergenic: Int?,
    val indoor: Int?,
    val lap: Int?,
    val adaptability: Int?,
    val affectionLevel: Int?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val catFriendly: Int?,
    val energyLevel: Int?,
    val grooming: Int?,
    val healthIssues: Int?,
    val intelligence: Int?,
    val sheddingLevel: Int?,
    val socialNeeds: Int?,
    val strangerFriendly: Int?,
    val vocalisation: Int?,
    val bidability: Int?,
    val referenceImageId: String?,
    val image: CatImageEntity?
)

data class CatWeightEntity(
    val imperial: String,
    val metric: String,
)

data class CatImageEntity(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
)