package com.example.kenanganbakery.presentation.ui.screen.pelanggan.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BakeryCheckoutScreen() {
    var quantity by remember { mutableStateOf(1) }
    var selectedPayment by remember { mutableStateOf("Gopay") }

    val bgColor = Color(0xFFFFF5E6)
    val cardColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    "Kenangan Bakery, Cipayung",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* Handle back */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = bgColor
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Delivery Location Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            "Delivery location",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Jl. Bambu Hitam No. 3, RT.3/RW.1, Bambu Apus, Kec. Cipayung, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13890",
                        fontSize = 11.sp,
                        color = Color.DarkGray,
                        lineHeight = 16.sp
                    )
                }
            }

            // Product Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Cheese cake",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "99.000",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                                    .clickable { if (quantity > 1) quantity-- },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("-", fontSize = 16.sp, color = Color.Gray)
                            }
                            Text(
                                "${quantity}x",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                                    .clickable { quantity++ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("+", fontSize = 16.sp, color = Color.Gray)
                            }
                        }
                    }
                    // Placeholder image
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFF4E6))
                    )
                }
            }

            // Payment Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Payment",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    PaymentRow("Price", "Rp 99.000")
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentRow("Delivery fee", "Rp 14.000")
                    Spacer(modifier = Modifier.height(8.dp))
                    PaymentRow("Other fee", "Rp 2.000")
                    Spacer(modifier = Modifier.height(12.dp))

                    Divider(color = Color.LightGray)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Total payment",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "Rp 115.000",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Payment Method Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Payment method",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Text(
                            "Lihat semua",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    PaymentMethodItem(
                        "ShopeePay",
                        Color(0xFFFF5722),
                        selectedPayment == "ShopeePay"
                    ) { selectedPayment = "ShopeePay" }

                    Spacer(modifier = Modifier.height(12.dp))

                    PaymentMethodItem(
                        "Gopay",
                        Color(0xFF00AA13),
                        selectedPayment == "Gopay"
                    ) { selectedPayment = "Gopay" }

                    Spacer(modifier = Modifier.height(12.dp))

                    PaymentMethodItem(
                        "Ovo",
                        Color(0xFF4C3494),
                        selectedPayment == "Ovo"
                    ) { selectedPayment = "Ovo" }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Place Order Button
            Button(
                onClick = { /* Handle order */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A4A4A)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Place order",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun PaymentRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 13.sp, color = Color.Gray)
        Text(value, fontSize = 13.sp, color = Color.DarkGray)
    }
}

@Composable
fun PaymentMethodItem(
    name: String,
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    name.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Text(name, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }

        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF4A4A4A)
            )
        )
    }
}

@Preview
@Composable
private fun CheckoutScreenPreview() {
    BakeryCheckoutScreen()
}