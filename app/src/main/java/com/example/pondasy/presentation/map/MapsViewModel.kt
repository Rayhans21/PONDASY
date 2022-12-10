package com.example.pondasy.presentation.map

import android.annotation.SuppressLint
import android.app.Application
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Intent
import android.location.Location
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pondasy.MyLocationSource
import com.example.pondasy.domain.repository.SpotRepository
import com.example.pondasy.presentation.camera.DefaultLocationClient
import com.example.pondasy.presentation.camera.LocationClient
import com.google.android.gms.location.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("UnspecifiedImmutableFlag")
@HiltViewModel
class MapsViewModel @Inject constructor(

    application: Application
) : AndroidViewModel(application) {
    var state by mutableStateOf(MapState())
    val locationSource = MyLocationSource()

    private var locationClient: LocationClient = DefaultLocationClient(
        getApplication<Application>().applicationContext,
        LocationServices.getFusedLocationProviderClient(getApplication<Application>().applicationContext)
    )
    private var lat: Double = 0.0
    private var long: Double = 0.0

    init {
        viewModelScope.launch {


        }
    }


    val locationFlow = locationClient.getLocationUpdates(10000L).catch { e ->
        e.printStackTrace()
    }.onEach { location ->
        lat = location.latitude.toString().takeLast(3).toDouble()
        long = location.longitude.toString().takeLast(3).toDouble()

    }

    fun newLocation(): Location {
        val location = Location("MyLocationProvider")
        location.apply {
            latitude = lat
            longitude = long
        }
        return location
    }




    fun setSaldo(now:String){
        val database = Firebase.database
        val myRef = database.getReference("saldo")

        myRef.setValue(now)
    }
    fun getSaldo(){
        val database = Firebase.database
        val myRef = database.getReference("saldo")
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                val value = snapshot.getValue<String>()
                state = state.copy(
                    saldo = value.toString()
                )
                Log.d(ContentValues.TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
    }



}