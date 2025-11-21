package com.example.kenanganbakery.presentation.ui.screen.pelanggan.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.domain.models.tab.TabBarItem
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice

// Data Classes
data class OrderItem(
    val name: String,
    val size: String,
    val quantity: Int
)

data class Order(
    val id: Int,
    val date: String,
    val status: String,
    val items: List<OrderItem>,
    val total: Int,
    val deliveryAddress: String,
    val deliveryDate: String,
    val paymentMethod: String
)

data class PaymentItem(
    val name: String,
    val price: Int,
    val quantity: Int,
    val emoji: String
)

@Composable
fun HistoryScreen() {
    var currentView by remember { mutableStateOf("history") }

    when (currentView) {
        "history" -> OrderHistoryScreen(onOrderClick = { currentView = "detail" })
        "detail" -> DetailPesananScreen(onBackClick = { currentView = "history" })
    }
}

@Composable
fun OrderHistoryScreen(onOrderClick: () -> Unit) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabBarItem(
            title = "Semua",
            value = "all"
        ),
        TabBarItem(
            title = "Diproses",
            value = "all"
        ),
        TabBarItem(
            title = "Dikirim",
            value = "all"
        ),
        TabBarItem(
            title = "Selesai",
            value = "done"
        )
    )

    val orders = listOf(
        Order(
            1, "December 28th, 2022", "Completed",
            listOf(
                OrderItem("Caffe Mocha", "Regular", 1),
                OrderItem("Hazelnut Latte", "Regular", 1)
            ),
            10000, "Jl. Kpg Sutoyo", "2025-10-21", "Kartu Debit/Kredit"
        ),
        Order(
            2, "December 29th, 2022", "Completed",
            listOf(OrderItem("Flat White", "Regular", 1)),
            8000, "Jl. Kpg Sutoyo", "2025-10-21", "Kartu Debit/Kredit"
        ),
        Order(
            3, "December 29th, 2022", "Completed",
            listOf(OrderItem("Caramel Macchiato", "Regular", 1)),
            12000, "Jl. Kpg Sutoyo", "2025-10-21", "Kartu Debit/Kredit"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ORDER HISTORY",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(24.dp))

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    tabs.forEachIndexed { index, tabBarItem ->
                        Tab(
                            onClick = {
                                selectedTabIndex = index

                            },
                            selected = selectedTabIndex == index
                        ) {
                            ModernText(
                                modifier = Modifier.padding(12.dp),
                                text = tabBarItem.title
                            )
                        }
                    }
                }
            }
        }

        // Orders List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(orders) { order ->
                OrderCard(order = order, onClick = onOrderClick)
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, onClick: () -> Unit) {
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
                            text = "Kenangan Bakery - TMII",
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
                        text = "Rp ${order.total.formatPrice()}",
                        size = 12,
                        weight = FontWeight.Bold
                    )
                    ModernText(
                        text = order.date,
                        size = 12,
                        weight = FontWeight.Bold
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPesananScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF3))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFBF3))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Text(
                text = "Detail Pesanan",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Order Date
            item {
                Text(
                    text = "2025 - October - 31, 08:03 AM",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Delivery Address
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Delivery location",
                            fontSize = 13.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Jl. Bambu Hitam No.3, RT.3/RW.1, Bambu Apus, Kec. Cipayung, Kota Jakarta Timur, Daerah Khusus Ibukota Jakarta 13890",
                            fontSize = 13.sp,
                            color = Color.DarkGray,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Order Item
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Cheese cake",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Text(
                                text = "99.000",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "1x",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🍰", fontSize = 60.sp)
                        }
                    }
                }
            }

            // Payment Details
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Payment",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )

                        PriceRowDetail("Price", "99.000")
                        PriceRowDetail("Delivery fee", "14.000")
                        PriceRowDetail("Other fee", "2.000")

                        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total payment",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Rp 115.000",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "˅",
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            // Payment Method
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Payment method",
                            fontSize = 13.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF00AAE8)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "G",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = "Gopay",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            // Transaction ID
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "No. Pesanan",
                            fontSize = 13.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "PK1234598765434B",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )

                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Copy",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun PriceRowDetail(label: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "Rp $amount",
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}