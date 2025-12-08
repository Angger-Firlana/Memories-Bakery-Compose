package com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.data.local.BranchManager
import com.example.kenanganbakery.domain.models.production_schedule.ProductionScheduleDetail
import com.example.kenanganbakery.presentation.ui.component.dialog.ErrorDialog
import com.example.kenanganbakery.presentation.ui.component.dialog.SuccessDialog
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard.component.DialogUpdateStatus
import com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard.component.ProductionScheduleDetailCard
import com.example.kenanganbakery.presentation.viewmodel.ProductionScheduleViewModel

@Composable
fun DashboardPetugasScreen(modifier: Modifier = Modifier, productionScheduleViewModel: ProductionScheduleViewModel) {
    val context = LocalContext.current
    val branchManager = BranchManager(context)
    val branch = branchManager.getBranch()

    val schedules by productionScheduleViewModel.schedules.collectAsState()
    val state by productionScheduleViewModel.state.collectAsState()
    var selectedDetail by remember { mutableStateOf<ProductionScheduleDetail?>(null) }
    var showUpdateStatus by remember { mutableStateOf(false) }
    var showDialogSuccess by remember { mutableStateOf<Boolean?>(null) }


    LaunchedEffect(Unit) {
        productionScheduleViewModel.getAllSchedule()
    }

    LaunchedEffect(state) {
        state?.let {
            if (it){
                showDialogSuccess = true
            }else{
                showDialogSuccess = false
            }
            productionScheduleViewModel.clearState()
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
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
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
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



                    ModernText(
                        text = schedules?.get(0)?.production_schedule_details?.sumOf { it.quantity }
                            .toString(),
                        size = 32,
                        weight = FontWeight.Bold
                    )

                    ModernText(
                        text = "Total roti yang harus dibuat",
                        size = 14,
                        color = Color.LightGray,
                        weight = FontWeight.Normal
                    )
                }
            }

            Spacer(
                Modifier.height(16.dp)
            )

            ModernText(
                text = "Daftar Produksi"
            )

            LazyColumn(

            ) {
                schedules?.get(0)?.production_schedule_details?.let { productionScheduleDetails ->
                    items(productionScheduleDetails) { productionScheduleDetail ->
                        ProductionScheduleDetailCard(
                            productionDetail = productionScheduleDetail,
                            onClickDetail = {
                                selectedDetail = productionScheduleDetail
                                showUpdateStatus = true
                            }
                        )
                    }
                }

            }

        }

        if (showUpdateStatus) {
            selectedDetail?.let { selectedDetail ->
                DialogUpdateStatus(
                    productionScheduleDetail = selectedDetail,
                    onDismiss = { showUpdateStatus = false },
                    onSave = {status ->
                        productionScheduleViewModel.updateStatusDetail(
                            id = selectedDetail.id,
                            status = status
                        )
                    }
                )
            }

        }

        showDialogSuccess?.let { showSuccess->
            if (showSuccess){
                SuccessDialog(
                    title = "Berhasil",
                    message = "Berhasil mengubah status detail produksi",
                    onDismissRequest = {
                        showDialogSuccess = null
                    }
                )
            }else{
                ErrorDialog(
                    title = "Gagal",
                    message = "Gagal mengubah status detail produksi",
                    onDismissRequest = {
                        showDialogSuccess = null
                    }
                )
            }

        }
    }

}