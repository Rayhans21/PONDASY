package com.example.pondasy.presentation.home.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.pondasy.R

import com.example.pondasy.presentation.camera.LocationService

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Greeting(

saldo : String,


    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
) {
    val mContext = LocalContext.current
    LazyColumn(
        contentPadding = innerPadding,
    ) {
        item {

                ConstraintLayout() {
                    val(box1,box3) = createRefs()

                    Box(modifier=modifier.constrainAs(box1){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }) {
                        Row(
                            modifier
                                .background(color = Color(0xFF008CEB))
                                .fillMaxWidth().height(200.dp)
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                            Image(
                                modifier = modifier.size(140.dp),
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "logo"
                            )
                            Image(
                                modifier = modifier.padding(end = 6.dp,top=50.dp),
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )

                        }
                    }
                    Box(
                        Modifier.padding(top=100.dp)
                    ){
                        Card(
                            modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp))
                                .padding(16.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {


                                Row(
                                    modifier = modifier
                                        .padding(16.dp)
                                        .clip(RoundedCornerShape(30.dp))
                                        .background(Color.White)
                                        .padding(4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Saldo")
                                    Spacer(modifier.width(200.dp))
                                    Text("Rp.${saldo} -")
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier
                                            .padding(4.dp)
                                            .clip(RoundedCornerShape(30.dp))
                                            .padding(16.dp)
                                            .clickable { },

                                        ) {
                                        Column(
                                            modifier = modifier
                                                .background(Color.White)
                                                .width(80.dp)
                                                .clip(RoundedCornerShape(30.dp))
                                                .padding(4.dp),

                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_baseline_discount_24),
                                                contentDescription = "Settings"
                                            )
                                            Text("Voucher")
                                        }

                                    }
                                    Card(
                                        modifier
                                            .padding(4.dp)
                                            .clip(RoundedCornerShape(30.dp))
                                            .padding(16.dp)
                                            .clickable { }
                                    ) {
                                        Column(
                                            modifier = modifier
                                                .background(Color.White)
                                                .width(100.dp)
                                                .clip(RoundedCornerShape(30.dp))
                                                .padding(4.dp),

                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                imageVector = Icons.Default.Notifications,
                                                contentDescription = "Settings"
                                            )
                                            Text("Notification")
                                        }
                                    }
                                    Card(
                                        modifier
                                            .padding(4.dp)
                                            .clip(RoundedCornerShape(30.dp))
                                            .padding(16.dp)
                                            .clickable { },

                                        ) {
                                        Column(
                                            modifier = modifier
                                                .background(Color.White)
                                                .width(100.dp)
                                                .clip(RoundedCornerShape(30.dp))
                                                .padding(4.dp),

                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                                                contentDescription = "Settings"
                                            )
                                            Text("Add")
                                        }
                                    }
                                }
                            }
                        }
                    }
                Box(modifier=modifier.constrainAs(box3){
                        top.linkTo(box1.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }) {
                    Column(modifier=modifier.padding(top=100.dp ) ) {
                        LazyRow() {
                            item {
                                Image(
                                    modifier = modifier
                                        .width(200.dp)
                                        .height(100.dp),
                                    painter = painterResource(id = R.drawable.image1),
                                    contentDescription = "Settings"
                                )
                                Image(
                                    modifier = modifier
                                        .width(200.dp)
                                        .height(100.dp),
                                    painter = painterResource(id = R.drawable.image3),
                                    contentDescription = "Settings"
                                )
                            }

                        }
                        LazyRow() {
                            item {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        modifier = modifier
                                            .width(200.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.image2),
                                        contentDescription = "Settings"
                                    )
                                    Text("Alfamaret Suka Karya")
                                    Row {
                                        Text("0.94km")
                                        Spacer(modifier = modifier.width(16.dp))
                                        Text("IDR 2.000", color = Color(0xFF89CFF0))

                                    }
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("Yok Parkir ->")
                                    }
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Image(
                                        modifier = modifier
                                            .width(200.dp)
                                            .height(100.dp),
                                        painter = painterResource(id = R.drawable.image2),
                                        contentDescription = "Settings"
                                    )
                                    Text("Alfamaret Suka Karya")
                                    Row {
                                        Text("0.94km")
                                        Spacer(modifier = modifier.width(16.dp))
                                        Text("IDR 2.000", color = Color(0xFF89CFF0))

                                    }
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("Yok Parkir ->")
                                    }
                                }

                            }
                        }
                    }
                    }
                }
        }
    }
}