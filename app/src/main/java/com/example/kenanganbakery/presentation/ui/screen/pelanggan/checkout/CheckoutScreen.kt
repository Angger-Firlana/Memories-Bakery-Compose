package com.example.kenanganbakery.presentation.ui.screen.pelanggan.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kenanganbakery.data.local.CartManager
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BakeryCheckoutScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val cartManager = CartManager(context)
    val cartItems = cartManager.getCart()

    // Store quantities for each cart item
    val quantities = remember { mutableStateMapOf<Int, Int>().apply {
        cartItems?.forEachIndexed { index, _ ->
            this[index] = 1
        }
    }}

    var selectedPayment by remember { mutableStateOf("cash") }
    var showEditLocationDialog by remember { mutableStateOf(false) }
    var deliveryAddress by remember {
        mutableStateOf("Jl. Bambu Hitam No. 3, RT.3/RW.1, Bambu Apus, Kec. Cipayung, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13890")
    }

    // Calculate totals
    val subtotal = cartItems?.sumOf { it.menu.price * (quantities[cartItems.indexOf(it)] ?: 1) } ?: 0
    val deliveryFee = 14000
    val otherFee = 2000
    val totalPayment = subtotal + deliveryFee + otherFee

    val bgColor = Color(0xFFF5F5F5)
    val cardColor = Color.White
    val primaryColor = Color(0xFF6D4C41)

    // Edit Location Dialog
    if (showEditLocationDialog) {
        EditLocationDialog(
            currentAddress = deliveryAddress,
            onDismiss = { showEditLocationDialog = false },
            onSave = { newAddress ->
                deliveryAddress = newAddress
                showEditLocationDialog = false
            },
            primaryColor = primaryColor
        )
    }

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
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = cardColor
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Delivery Location Card
            item {
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
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable { showEditLocationDialog = true },
                                tint = primaryColor
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            deliveryAddress,
                            fontSize = 11.sp,
                            color = Color.DarkGray,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Product Cards
            cartItems?.let { items ->
                itemsIndexed(items) { index, cartItem ->
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
                                ModernText(
                                    text = cartItem.menu.name,
                                    size = 15,
                                    weight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    cartItem.menu.price.toRupiah(),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = primaryColor
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
                                            .clickable {
                                                quantities[index]?.let { qty ->
                                                    if (qty > 1) quantities[index] = qty - 1
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("-", fontSize = 16.sp, color = Color.Gray)
                                    }
                                    Text(
                                        "${quantities[index] ?: 1}x",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = primaryColor
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                                            .clickable {
                                                quantities[index]?.let { qty ->
                                                    quantities[index] = qty + 1
                                                }
                                            },
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
                                    .background(Color(0xFFE0E0E0))
                            )
                        }
                    }
                }
            }

            // Payment Card
            item {
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

                        PaymentRow("Price", subtotal.toRupiah())
                        Spacer(modifier = Modifier.height(8.dp))
                        PaymentRow("Delivery fee", deliveryFee.toRupiah())
                        Spacer(modifier = Modifier.height(8.dp))
                        PaymentRow("Other fee", otherFee.toRupiah())
                        Spacer(modifier = Modifier.height(12.dp))

                        HorizontalDivider(color = Color.LightGray)
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
                                totalPayment.toRupiah(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                    }
                }
            }

            // Payment Method Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = cardColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Payment method",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        PaymentMethodItem(
                            "Cash",
                            "cash",
                            selectedPayment == "cash"
                        ) { selectedPayment = "cash" }

                        Spacer(modifier = Modifier.height(12.dp))

                        PaymentMethodItem(
                            "QRIS",
                            "qris",
                            selectedPayment == "qris"
                        ) { selectedPayment = "qris" }

                        Spacer(modifier = Modifier.height(12.dp))

                        PaymentMethodItem(
                            "Transfer",
                            "transfer",
                            selectedPayment == "transfer"
                        ) { selectedPayment = "transfer" }

                        Spacer(modifier = Modifier.height(12.dp))

                        PaymentMethodItem(
                            "BCA",
                            "bca",
                            selectedPayment == "bca"
                        ) { selectedPayment = "bca" }

                        Spacer(modifier = Modifier.height(12.dp))

                        PaymentMethodItem(
                            "Other",
                            "other",
                            selectedPayment == "other"
                        ) { selectedPayment = "other" }
                    }
                }
            }

            // Place Order Button
            item {
                Button(
                    onClick = { /* Handle order */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor
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
}

@Composable
fun EditLocationDialog(
    currentAddress: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    primaryColor: Color
) {
    var addressText by remember { mutableStateOf(currentAddress) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "Edit Delivery Location",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = addressText,
                    onValueChange = { addressText = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Address") },
                    minLines = 4,
                    maxLines = 6,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryColor,
                        focusedLabelColor = primaryColor,
                        cursorColor = primaryColor
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = primaryColor
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            if (addressText.isNotBlank()) {
                                onSave(addressText)
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Save")
                    }
                }
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
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val primaryColor = Color(0xFF6D4C41)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 4.dp),
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
                    .background(if (isSelected) primaryColor else Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    name.first().toString(),
                    color = if (isSelected) Color.White else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Text(
                name,
                fontSize = 14.sp,
                color = if (isSelected) primaryColor else Color(0xFFE0E0E0),
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
            )
        }

        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = primaryColor
            )
        )
    }
}

private fun Int.toRupiah(): String {
    val locale = Locale("id", "ID")
    val currency = Currency.getInstance(locale)
    val format = NumberFormat.getCurrencyInstance(locale)
    format.currency = currency

    return format.format(this.toDouble())
}