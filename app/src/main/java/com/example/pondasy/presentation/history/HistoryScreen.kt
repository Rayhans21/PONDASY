package com.example.pondasy.presentation.history

import android.text.style.TabStopSpan
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*


import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pondasy.presentation.history.components.TabScreenOne
import com.example.pondasy.presentation.history.components.TabScreenTwo
import com.example.pondasy.ui.theme.PondasyTheme
import com.google.accompanist.pager.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Activity",
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
                  ,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = innerPadding,
            ) {
                item {

                  Toolbar()
                    TabScreen()
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(modifier = Modifier.background(Color.Black),
        title = { Text(text = "TabLayout", color = Color.White) })
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen() {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.background(Color.White)) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("Aktivitas", "Riwayat")
    val scope = rememberCoroutineScope()
    androidx.compose.material.TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color(0xFF5463E9),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState,
                    tabPositions
                )
            )
        }) {
        list.forEachIndexed { index, _ ->

            Tab(
                text = {
                    Text(
                        text = list[index],
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(count = 3, state = pagerState) { page ->
        when (page) {
            0 -> TabScreenOne(tabName = "Aktivitas")
            1 -> TabScreenTwo(tabName = "Riwayat")
        }
    }
}

