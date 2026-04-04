package com.nicolascommandeur.wikicat.data.mappers

import com.nicolascommandeur.wikicat.data.local.entities.CatBreedEntity
import com.nicolascommandeur.wikicat.data.local.entities.CatImageEntity
import com.nicolascommandeur.wikicat.data.local.entities.CatWeightEntity
import com.nicolascommandeur.wikicat.data.local.entities.TheCatApiVersionEntity
import com.nicolascommandeur.wikicat.data.remote.models.CatBreedDto
import com.nicolascommandeur.wikicat.data.remote.models.CatImageDto
import com.nicolascommandeur.wikicat.data.remote.models.CatWeightDto
import com.nicolascommandeur.wikicat.data.remote.models.TheCatApiVersionDto
import com.nicolascommandeur.wikicat.domain.models.CatBreed
import com.nicolascommandeur.wikicat.domain.models.CatImage
import com.nicolascommandeur.wikicat.domain.models.CatWeight

// ? DTO to ENTITY
fun TheCatApiVersionDto.toEntity(): TheCatApiVersionEntity = TheCatApiVersionEntity(id = 0, version = version)

fun CatBreedDto.toEntity(
    isFavorite: Boolean?
): CatBreedEntity = CatBreedEntity(
    id = id,
    name = name,
    weight = weight.toEntity(),
    cfaUrl = cfaUrl,
    vetStreetUrl = vetStreetUrl,
    vcaHospitalsUrl = vcaHospitalsUrl,
    wikipediaUrl = wikipediaUrl,
    temperament = temperament,
    origin = origin,
    countryCode = countryCode,
    description = description,
    lifeSpan = lifeSpan,
    altNames = altNames,
    indoor = indoor,
    lap = lap,
    adaptability = adaptability,
    affectionLevel = affectionLevel,
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    catFriendly = catFriendly,
    strangerFriendly = strangerFriendly,
    energyLevel = energyLevel,
    grooming = grooming,
    healthIssues = healthIssues,
    intelligence = intelligence,
    sheddingLevel = sheddingLevel,
    socialNeeds = socialNeeds,
    vocalisation = vocalisation,
    experimental = experimental,
    hairless = hairless,
    natural = natural,
    rare = rare,
    rex = rex,
    suppressedTail = suppressedTail,
    shortLegs = shortLegs,
    hypoallergenic = hypoallergenic,
    bidability = bidability,
    referenceImageId = referenceImageId,
    image = image?.toEntity(),
    isFavorite = isFavorite ?: false
)

fun CatWeightDto.toEntity(): CatWeightEntity = CatWeightEntity(imperial, metric)
fun CatImageDto.toEntity(): CatImageEntity = CatImageEntity(id, width, height, url)

// ? DAO to DOMAIN
fun CatBreedEntity.toDomain(): CatBreed = CatBreed(
    id = id,
    name = name,
    weight = weight.toDomain(),
    cfaUrl = cfaUrl,
    vetStreetUrl = vetStreetUrl,
    vcaHospitalsUrl = vcaHospitalsUrl,
    wikipediaUrl = wikipediaUrl,
    temperament = temperament,
    origin = origin,
    countryCode = countryCode,
    description = description,
    altNames = altNames,
    lifeSpan = lifeSpan,
    indoor = indoor,
    lap = lap,
    adaptability = adaptability,
    affectionLevel = affectionLevel,
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    catFriendly = catFriendly,
    strangerFriendly = strangerFriendly,
    energyLevel = energyLevel,
    grooming = grooming,
    healthIssues = healthIssues,
    intelligence = intelligence,
    sheddingLevel = sheddingLevel,
    socialNeeds = socialNeeds,
    vocalisation = vocalisation,
    bidability = bidability,
    experimental = experimental,
    hairless = hairless,
    natural = natural,
    rare = rare,
    rex = rex,
    suppressedTail = suppressedTail,
    shortLegs = shortLegs,
    hypoallergenic = hypoallergenic,
    referenceImageId = referenceImageId,
    image = image?.toDomain(),
    isFavorite = isFavorite
)

fun CatWeightEntity.toDomain(): CatWeight = CatWeight(imperial, metric)
fun CatImageEntity.toDomain(): CatImage = CatImage(id, width, height, url)