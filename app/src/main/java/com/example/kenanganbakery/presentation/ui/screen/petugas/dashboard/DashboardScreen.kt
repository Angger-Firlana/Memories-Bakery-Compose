package com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.viewmodel.ProductionScheduleViewModel

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, productionScheduleViewModel: ProductionScheduleViewModel) {
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            Modifier.fillMaxSize().padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                elevation = CardDefaults.cardElevation(
                    6.dp
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ModernText(
                            text = "Target Hari Ini"
                        )

                        Spacer(Modifier.weight(1f))

                        Card(
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.LightGray
                            )

                        ) {
                            ModernText(
                                modifier = Modifier.padding(8.dp),
                                text = ""
                            )
                        }
                    }
                }
            }
        }
    }
}