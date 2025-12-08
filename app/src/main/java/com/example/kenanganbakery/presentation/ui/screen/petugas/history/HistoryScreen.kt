package com.example.kenanganbakery.presentation.ui.screen.petugas.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.data.local.BranchManager
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard.component.ProductionScheduleDetailCard
import com.example.kenanganbakery.presentation.ui.screen.petugas.history.component.FilterDataCard
import com.example.kenanganbakery.presentation.ui.screen.petugas.history.component.ProductionScheduleDetailHistoryCard
import com.example.kenanganbakery.presentation.viewmodel.ProductionScheduleViewModel

@Composable
fun HistoryPetugasScreen(modifier: Modifier = Modifier, productionScheduleViewModel: ProductionScheduleViewModel) {
    val context = LocalContext.current
    val branchManager = BranchManager(context)
    val branch = branchManager.getBranch()

    val productionSchedules by productionScheduleViewModel.schedules.collectAsState()

    LaunchedEffect(Unit) {
        productionScheduleViewModel.getAllSchedule()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModernText(
                        text = "Riwayat Produksi"
                    )

                    Spacer(Modifier.weight(1f))

                    Card(
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )

                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.LocationOn,
                                null
                            )

                            Spacer(Modifier.width(6.dp))

                            ModernText(

                                text = branch?.name ?: "Kenangan Bakery",
                                size = 10
                            )
                        }

                    }
                }
            }

            FilterDataCard(
                modifier = Modifier.padding(bottom = 16.dp)
            )

            productionSchedules?.let { productionSchedules ->
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    productionSchedules.forEach {productionSchedule ->
                        productionSchedule.production_schedule_details.forEach { productionScheduleDetail ->
                            item {
                                ProductionScheduleDetailHistoryCard(
                                    productionDetail = productionScheduleDetail
                                )
                            }
                        }
                    }
                }
            }


        }
    }
}