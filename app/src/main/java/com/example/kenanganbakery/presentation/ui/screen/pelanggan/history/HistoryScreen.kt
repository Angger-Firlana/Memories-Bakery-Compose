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
import com.example.kenanganbakery.domain.models.order.Order
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.history.component.OrderCard
import com.example.kenanganbakery.domain.models.tab.TabBarItem
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice
import com.example.kenanganbakery.presentation.viewmodel.OrderViewModel

// Data Classes
data class OrderItem(
    val name: String,
    val size: String,
    val quantity: Int
)


data class PaymentItem(
    val name: String,
    val price: Int,
    val quantity: Int,
    val emoji: String
)

@Composable
fun HistoryScreen(orderViewModel: OrderViewModel) {
    var selectedOrder by remember {mutableStateOf(0)}
    var currentView by remember { mutableStateOf("history") }

    when (currentView) {
        "history" -> OrderHistoryScreen(onOrderClick = { currentView = "detail"; selectedOrder = it }, orderViewModel = orderViewModel)
        "detail" -> DetailPesananScreen(onBackClick = { currentView = "history" }, orderId = selectedOrder, orderViewModel = orderViewModel)
    }
}

@Composable
fun OrderHistoryScreen(onOrderClick: (Int) -> Unit, orderViewModel:OrderViewModel) {
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

    val orders by orderViewModel.orders.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.getAllOrders()
    }


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
                OrderCard(order = order, onClick = {onOrderClick(order.id)})
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