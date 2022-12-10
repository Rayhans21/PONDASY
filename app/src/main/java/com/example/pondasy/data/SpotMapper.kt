package com.example.pondasy.data

import com.example.pondasy.domain.model.Spot

fun SpotEntity.toSpot(): Spot {
    return Spot(
     isOnEria=isOnEria,
        id=id
    )
}
fun Spot.toSpotEntity(): SpotEntity {
    return SpotEntity(
        isOnEria= isOnEria,
        id=id
    )
}