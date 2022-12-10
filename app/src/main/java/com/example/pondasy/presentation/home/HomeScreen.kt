package com.example.pondasy.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pondasy.R
import com.example.pondasy.presentation.destinations.*
import com.example.pondasy.presentation.home.components.Greeting
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)

@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Activity","Scan", "Wallets","Profile")
LaunchedEffect(key1 =true){
    viewModel.getSaldo()

}
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                    icon = {
                            when (index) {
                                0 -> {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Home"
                                    )
                                }
                                1 -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_baseline_history_24),
                                        contentDescription = "activity"
                                    )
                                }
                                2 -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_baseline_qr_code_scanner_24),
                                        contentDescription = "Scan"
                                    )
                                }
                                3 -> {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_baseline_account_balance_wallet_24),

                                        contentDescription = "Wallets"
                                    )
                                }
                                4 -> {
                                    Icon(
                                   imageVector=Icons.Default.Person,

                                        contentDescription = "Profile"
                                    )
                                }
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index
                            when (index) {
                                0->{
                                    navigator.navigate(HomeScreenDestination){
                                        popUpTo(HomeScreenDestination.route) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                }
                                1->{
                                    navigator.navigate(HistoryScreenDestination){
                                        popUpTo(HomeScreenDestination.route) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }

                                }
                                2 -> {
                                    navigator.navigate(CameraScreenDestination)
                                }
                                3->{
                                    navigator.navigate(WalletScreenDestination)
                                }
                                4 -> {
                                    navigator.navigate(ProfileScreenDestination)
                                }
                            } }
                    )
                }
            }
        },
        content = { innerPadding ->
            Greeting(

                innerPadding =      innerPadding,
                saldo = viewModel.state.saldo,




            )

        }
    )

}