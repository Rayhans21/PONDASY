package com.example.pondasy.presentation.map


import com.example.pondasy.domain.model.Spot

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ),
    var isMapLoaded: Boolean = false,

    val parkingSpots: List<Spot> = emptyList(),
    val isFalloutMap: Boolean = false,
    val saldo:String="0",
)
