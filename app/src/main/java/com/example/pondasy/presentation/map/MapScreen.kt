package com.example.pondasy.presentation.camera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pondasy.domain.model.Data
import com.example.pondasy.presentation.destinations.HomeScreenDestination
import com.example.pondasy.presentation.home.HomeViewModel
import com.example.pondasy.presentation.map.MapsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RequiresApi(Build.VERSION_CODES.Q)
@Destination
@Composable
fun MapScreen(
    navigator: DestinationsNavigator,
    code: String,
    viewModel: MapsViewModel = hiltViewModel(),
    viewModelHome: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        val gson = Gson()
        val tengah = LatLng(0.5148207428685511, 101.44426262754946)

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(tengah, 19f)
        }


        val context = LocalContext.current
        val uiSettings = remember {
            MapUiSettings(zoomControlsEnabled = false)
        }
        var hasBackgroundPermission by remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
        }
        val launcherPermission = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasBackgroundPermission = granted
        }

        val locationState = viewModel.locationFlow.collectAsState(initial = viewModel.newLocation())
        LaunchedEffect(key1 = true) {
            launcherPermission.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        }
        LaunchedEffect(key1 = true) {
            viewModel.getSaldo()
        }
        LaunchedEffect(locationState.value) {
            Log.d(ContentValues.TAG, "Updating blue dot on map...")
            viewModel.locationSource.onLocationChanged(locationState.value)

            Log.d(ContentValues.TAG, "Updating camera position...")
            val cameraPosition = CameraPosition.fromLatLngZoom(
                LatLng(
                    locationState.value.latitude,
                    locationState.value.longitude
                ), 19f
            )
            if (viewModel.state.isMapLoaded) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newCameraPosition(cameraPosition),
                    1_000
                )

            }
        }

        // Detect when the map starts moving and print the reason
        LaunchedEffect(cameraPositionState.isMoving) {
            if (cameraPositionState.isMoving) {
                Log.d(
                    ContentValues.TAG,
                    "Map camera started moving due to ${cameraPositionState.cameraMoveStartedReason.name}"
                )
            }
        }
        GoogleMap(
            modifier = modifier
                .fillMaxWidth(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,
            locationSource = viewModel.locationSource,

            onMapLoaded = {
                viewModel.state.isMapLoaded = true

            }
        ) {


        }
        if (!viewModel.state.isMapLoaded) {
            AnimatedVisibility(
                modifier = modifier
                    .matchParentSize(),
                visible = !viewModel.state.isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut(),


                ) {

                CircularProgressIndicator(
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )

            }

        }
        Column {
            Spacer(modifier.weight(1f))
            Column(
                modifier = modifier.background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Lokasi: ${gson.fromJson(code, Data::class.java).lokasi}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth(),
                    color = Color.Black
                )
                Text(
                    text = "Pukul: 4:53",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth(),

                    color = Color.Black
                )
                Text(
                    text = "Harga: ${gson.fromJson(code, Data::class.java).harga}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth(),

                    color = Color.Black
                )
                if (viewModel.state.saldo.toInt() <= 0 || viewModel.state.saldo.toInt() -  gson.fromJson(
                        code,
                        Data::class.java
                    ).harga.toInt() <=0
                ) {
                    Text("saldo tidak cukup")
                } else {
                    OutlinedButton(onClick = {
                        val now = (viewModel.state.saldo.toInt() - gson.fromJson(
                            code,
                            Data::class.java
                        ).harga.toInt()).toString()
                        viewModel.setSaldo(now)
                        navigator.navigate(HomeScreenDestination)
                    }) {
                        Text("Bayar")
                    }
                }
            }

        }

    }

}