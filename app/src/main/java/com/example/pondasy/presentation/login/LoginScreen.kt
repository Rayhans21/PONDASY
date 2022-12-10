package com.example.pondasy.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.pondasy.R
import com.example.pondasy.presentation.destinations.HomeScreenDestination
import com.example.pondasy.presentation.destinations.LoginScreenDestination
import com.example.pondasy.presentation.destinations.RegisterScreenDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
) {
    var state by remember { mutableStateOf(LoginState()) }
    Column(
        modifier = Modifier
            .background(Color(0xFF008CEB))
            .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Image(
            modifier =Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.login),
            contentDescription = "logo"
        )
        Text(color=Color.White,text="Login Into App")
        Text(color=Color.White,text="Masukan Username dan Password yang sesuai")

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
            value = state.password,
            onValueChange = {
                state = state.copy(
                        password = it
                        )
            },
            placeholder = { Text(color=Color.White,text="Password") }
        )
        SignInWithEmailAndPassword(
            email = state.email,
            password = state.password,
            onSuccess = {
                     navigator.navigate(HomeScreenDestination)
            },
            onError = {it.printStackTrace()}
        )
        Text(color=Color.White,text="or")
        Text(modifier=Modifier.clickable {
            navigator.navigate(RegisterScreenDestination)
        },color=Color.White,text="Register")
    }
}


@Composable
fun SignInWithEmailAndPassword(
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (Exception) -> Unit
) {
    Button(onClick = {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
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
        Text("Login")
    }

}