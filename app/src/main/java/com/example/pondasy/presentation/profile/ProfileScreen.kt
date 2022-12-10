package com.example.pondasy.presentation.profile


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Profile",
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

                   AsyncImage(
                       model = ImageRequest.Builder(LocalContext.current)
                           .data("https://avatars.dicebear.com/api/initials/Deboyyy.png",)
                           .crossfade(true)
                           .build(),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       modifier = modifier

                           .size(60.dp)
                           .clip(CircleShape)

                   )
                   Text(
                       text = "Deboyyy",
                       fontWeight = FontWeight.Medium,
                       modifier = modifier


                   )
                   Text(
                       text = "boydeboy@gmail.com",
                       fontWeight = FontWeight.Medium,
                       modifier = modifier


                   )
               }
           }
        })
}
