package com.example.kenanganbakery.presentation.ui.screen.pelanggan.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.domain.models.order.Order
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun OrderCard(order: Order, onClick: () -> Unit) {
    val parsedDate = OffsetDateTime.parse(order.created_at)
    val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color.LightGray.copy(alpha = 0.5f), shape = RoundedCornerShape(16.dp)).padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF8E1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFB8860B))
                        )
                    }


                    Column {
                        ModernText(
                            text = order.branch?.name ?: "Kenangan Bakery",
                            size = 12,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }

                    Spacer(Modifier.weight(1f))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        ModernText(
                            modifier = Modifier.padding(12.dp),
                            text = order.status,
                            size = 12
                        )
                    }

                }


            }


            Spacer(modifier = Modifier.height(12.dp))

            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.weight(1f)
                ) {
                    ModernText(
                        text = "Total Harga: ",
                        color = Color.Gray,
                        size = 12
                    )
                    ModernText(
                        text = "Tanggal pesanan: ",
                        color = Color.Gray,
                        size = 12
                    )
                    ModernText(
                        text = "Status ",
                        color = Color.Gray,
                        size = 12
                    )
                }

                Column(
                    Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    ModernText(
                        text = "Rp ${order.total.toDoubleOrNull()?.formatPrice()}",
                        size = 12,
                        weight = FontWeight.Bold
                    )
                    order.created_at?.let {
                        ModernText(
                            text = formattedDate,
                            size = 12,
                            weight = FontWeight.Bold
                        )
                    }
                    ModernText(
                        text = order.status,
                        size = 12,
                        weight = FontWeight.Bold
                    )
                }



            }
        }
    }
}