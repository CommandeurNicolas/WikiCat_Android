package com.nicolascommandeur.wikicat.domain.models

import androidx.compose.runtime.Immutable

@Immutable
class CatBreed(
    val id: String,
    val name: String,
    val weight: CatWeight,
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
    val indoor: Int?,
    val lap: Int?,
    val adaptability: Int?,
    val affectionLevel: Int?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val catFriendly: Int?,
    val strangerFriendly: Int?,
    val energyLevel: Int?,
    val grooming: Int?,
    val healthIssues: Int?,
    val intelligence: Int?,
    val sheddingLevel: Int?,
    val socialNeeds: Int?,
    val vocalisation: Int?,
    val bidability: Int?,
    val experimental: Int?,
    val hairless: Int?,
    val natural: Int?,
    val rare: Int?,
    val rex: Int?,
    val suppressedTail: Int?,
    val shortLegs: Int?,
    val hypoallergenic: Int?,
    val referenceImageId: String?,
    val image: CatImage?
) {
    val isExperimental: Boolean = experimental == 1
    val isHairless: Boolean = hairless == 1
    val isNatural: Boolean = natural == 1
    val isRare: Boolean = rare == 1
    val isRex: Boolean = rex == 1
    val hasSuppressedTail: Boolean = suppressedTail == 1
    val hasShortLegs: Boolean = shortLegs == 1
    val isHypoallergenic: Boolean = hypoallergenic == 1

    var isFavorite: Boolean = false
}

class CatWeight(
    val imperial: String,
    val metric: String
)

class CatImage(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
)