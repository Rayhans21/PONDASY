package com.example.pondasy.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pondasy.R
import com.example.pondasy.presentation.destinations.HomeScreenDestination
import com.example.pondasy.presentation.destinations.LoginScreenDestination
import com.example.pondasy.presentation.destinations.RegisterScreenDestination
import com.example.pondasy.presentation.login.LoginState
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun RegisterScreen(modifier: Modifier = Modifier,
                   navigator: DestinationsNavigator
) {
    var state by remember { mutableStateOf(RegisterState()) }
    Column(
        modifier = Modifier
            .background(Color(0xFF008CEB))
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
        ) {
        Image(
            modifier =Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.register),
            contentDescription = "logo"
        )
        Text(color=Color.White,text="Register")
        Text(color=Color.White,text="Masukkan data diri anda yang sesuai ya!")
        TextField(
            value = state.email,
            onValueChange = {
                state= state.copy(
                    email = it
                )
            },
            placeholder = { Text(color=Color.White,text="Email") }
        )
        TextField(
            value = state.no,
            onValueChange = {
                state= state.copy(
                    no = it
                )
            },
            placeholder = { Text(color=Color.White,text="No. Handphone") }
        )
        TextField(
            value = state.password,
            onValueChange = {
                state = state.copy(
                    password = it
                )
            },
            placeholder = { Text(color=Color.White,text="Password") }
        )
        TextField(
            value = state.passwordConfirm,
            onValueChange = {
                state = state.copy(
                    passwordConfirm = it
                )
            },
            placeholder = { Text(color=Color.White,text="Konfirmasi Password") }
        )
        SignUpWithEmailAndPassword(
            email = state.email,
            password = state.password,
            onSuccess = {
                navigator.navigate(LoginScreenDestination)
            },
            onError = {it.printStackTrace()}

        )
        Text(color=Color.White,text="or")
        Text(modifier=Modifier.clickable {
            navigator.navigate(LoginScreenDestination)
        },color=Color.White,text="Login")
    }
}


@Composable
fun SignUpWithEmailAndPassword(
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (Exception) -> Unit
) {
    Button(onClick = {
        try {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception!!)
                }
            }
        }catch(e:Exception){
            e.printStackTrace()
        }

    }) {
        Text("Registrasi")
    }

}