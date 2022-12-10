package com.example.pondasy.domain.repository

import com.example.pondasy.domain.model.Spot
import kotlinx.coroutines.flow.Flow

interface SpotRepository {
    suspend fun  insertParkingSpot(spot:Spot)
    suspend fun  deleteParkingSpot(spot: Spot)
    suspend fun  updateParkingSpot(onEria:Boolean)
    fun getParkingSpot(): Flow<List<Spot>>

}