package com.example.pondasy.presentation.addvehicle

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehicleScreen(modifier: Modifier = Modifier,
                     navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Wallet",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 15.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },

                )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = innerPadding,
            ) {
                item {


                    Text(
                        text = "Sambungkan Rekening atau E-wallet",
                        fontWeight = FontWeight.Medium,
                        modifier = modifier


                    )
                    Text(
                        text = "Pilih Rekening atau E-wallet",
                        fontWeight = FontWeight.Medium,
                        modifier = modifier


                    )
                }
            }
        })
}