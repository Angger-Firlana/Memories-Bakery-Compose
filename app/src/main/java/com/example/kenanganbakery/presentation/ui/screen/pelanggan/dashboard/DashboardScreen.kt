package com.example.kenanganbakery.presentation.ui.screen.pelanggan.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize()
    ){
        Column(
            Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(R.drawable.testingheadre),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth()
            )

            Row(
                Modifier.fillMaxWidth().padding(12.dp)
            ) {
                Card(
                    onClick = {

                    },
                    modifier = Modifier.weight(1f).height(120.dp).padding(6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    elevation = CardDefaults.cardElevation(
                        6.dp
                    )
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ModernText(text = "Pick Up")
                    }
                }

                Card(
                    onClick = {

                    },
                    modifier = Modifier.weight(1f).height(120.dp).padding(6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    elevation = CardDefaults.cardElevation(
                        6.dp
                    )
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ModernText(text = "Delivery")
                    }
                }
            }


            Card(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth().height(120.dp).padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(
                    6.dp
                )
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Column(
                        Modifier.weight(1.5f).padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ModernText(
                            text = "Cake gacorr ini wakkk",
                            size = 16,
                            weight = FontWeight.Bold
                        )

                        ModernText(
                            text = "New Product!",
                            size = 12,
                            weight = FontWeight.Normal
                        )

                        ModernButton(
                            text = "Order Now",
                            onClick = {

                            }
                        )
                    }
                    Column(
                        Modifier.weight(1f).padding(12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.logo),
                            contentDescription = null
                        )
                    }
                }

            }

        }
    }
}