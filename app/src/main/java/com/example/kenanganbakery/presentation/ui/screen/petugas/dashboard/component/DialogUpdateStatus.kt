package com.example.kenanganbakery.presentation.ui.screen.petugas.dashboard.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kenanganbakery.domain.models.production_schedule.ProductionScheduleDetail

data class Status(
    val name: String,
    val value: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogUpdateStatus(
    modifier: Modifier = Modifier,
    productionScheduleDetail: ProductionScheduleDetail,
    statusList: List<Status> = listOf(
        Status("Belum Dibuat", "pending"),
        Status("Sedang Diproses", "in_progress"),
        Status("Selesai", "completed")
    ),
    onDismiss: () -> Unit,
    onSave: (String) -> Unit = {}
) {
    var selectedStatus by remember { mutableStateOf(productionScheduleDetail.status) }
    var selectedStatusValue by remember { mutableStateOf(productionScheduleDetail.status) }
    var expanded by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "Update Status Produksi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Product Name Label
                Text(
                    text = "Nama produk",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                // Product Name Field (Read Only)
                OutlinedTextField(
                    value = productionScheduleDetail.menu.name,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color.LightGray,
                        disabledContainerColor = Color(0xFFF5F5F5)
                    ),
                    enabled = false,
                    shape = RoundedCornerShape(8.dp)
                )

                // Status Label
                Text(
                    text = "Status Produksi",
                    fontSize = 14.sp,
                    color = Color.Black
                )

                // Status Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedStatus,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.LightGray,
                            unfocusedBorderColor = Color.LightGray,
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        statusList.forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status.name) },
                                onClick = {
                                    selectedStatus = status.name
                                    selectedStatusValue = status.value
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    // Cancel Button
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray
                        )
                    ) {
                        Text("Batal")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Save Button
                    Button(
                        onClick = {
                            onSave(selectedStatusValue)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE0E0E0),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Simpan")
                    }
                }
            }
        }
    }
}