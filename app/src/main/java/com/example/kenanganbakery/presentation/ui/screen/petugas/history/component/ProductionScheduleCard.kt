package com.example.kenanganbakery.presentation.ui.screen.petugas.history.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.domain.models.production_schedule.ProductionSchedule
import com.example.kenanganbakery.domain.models.production_schedule.ProductionScheduleDetail
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun ProductionScheduleDetailHistoryCard(productionDetail: ProductionScheduleDetail, modifier: Modifier = Modifier) {
    Box(
        Modifier.fillMaxWidth().height(180.dp).padding(vertical = 12.dp)
    ){
        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.cardElevation(6.dp),
            content = {

            }
        )
        Card(
            onClick = {

            },
            modifier = Modifier.padding(start = 12.dp).fillMaxHeight(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // Status badge
                    Box(
                        modifier = Modifier
                            .background(
                                color = when (productionDetail.status) {
                                    "pending" -> Color(0xFFFFF4E6)
                                    "in_progress" -> Color(0xFFE3F2FD)
                                    "completed" -> Color(0xFFE8F5E9) // atau "done", "finished", dll
                                    else -> Color(0xFFE8F5E9)
                                },
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        ModernText(
                            text = when (productionDetail.status) {
                                "pending" -> "Belum Dibuat"
                                "in_progress" -> "Dalam Proses"
                                else -> "Sudah Dibuat"
                            },
                            color = when (productionDetail.status) {
                                "pending" -> Color(0xFFFF9800)
                                "in_progress" -> Color(0xFF2196F3)
                                else -> Color(0xFF4CAF50)
                            },
                            size = 10,
                            weight = FontWeight.Bold
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        ModernText(
                            text = productionDetail.menu.name,
                            size = 16,
                            weight = FontWeight.Bold
                        )

                        ModernText(
                            text = "Jumlah: ${productionDetail.quantity} pcs",
                            size = 10,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

            }
        }
    }
}