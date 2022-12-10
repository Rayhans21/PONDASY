package com.example.pondasy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpotEntity(
    val isOnEria:Boolean= false,
    @PrimaryKey val id:Int?=null
)
