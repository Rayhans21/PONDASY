package com.example.pondasy.presentation.map


import com.example.pondasy.domain.model.Spot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFalloutMap : MapEvent()
    data class OnMapLongClick(val latLng: LatLng) : MapEvent()
    data class OnInfoWindowLongClick(val spot: Spot) : MapEvent()
}