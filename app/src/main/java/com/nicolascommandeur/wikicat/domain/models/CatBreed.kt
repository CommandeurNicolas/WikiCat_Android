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

    fun flagFromCountryCode(): String {
        val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

    companion object {
        val testCatBreed = CatBreed(
            id = "aege",
            name = "Aegean",
            weight = CatWeight(imperial = "7 - 10", metric = "3 - 5"),
            cfaUrl = null,
            vetStreetUrl = null,
            vcaHospitalsUrl = null,
            wikipediaUrl = null,
            temperament = "Affectionate, Social, Intelligent, Playful, Active",
            origin = "Greece",
            countryCode = "GR",
            description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
            altNames = null,
            lifeSpan = "9 - 12",
            indoor = 0,
            lap = 1,
            adaptability = 5,
            affectionLevel = 4,
            childFriendly = 4,
            dogFriendly = 4,
            catFriendly = 3,
            energyLevel = 3,
            grooming = 3,
            healthIssues = 1,
            intelligence = 3,
            sheddingLevel = 3,
            socialNeeds = 4,
            strangerFriendly = 4,
            vocalisation = 3,
            bidability = 3,
            experimental = 1,
            hairless = 1,
            natural = 1,
            rare = 0,
            rex = 0,
            suppressedTail = 0,
            shortLegs = 0,
            hypoallergenic = 0,
            referenceImageId = "ozEvzdVM-",
            image = CatImage(
                id = "ozEvzdVM-",
                url = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",
                width = 1200,
                height = 800
            )
        )
    }
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