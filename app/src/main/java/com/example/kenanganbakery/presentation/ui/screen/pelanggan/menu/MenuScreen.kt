package com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kenanganbakery.domain.models.branch.Branch
import com.example.kenanganbakery.domain.models.menu.Menu
import com.example.kenanganbakery.domain.models.type.Type
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.BranchSelectionDialog
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.CartDialog
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.CategoryChip
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.DeliveryTab
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.ProductCard
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.ProductDetailDialog
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component.ProductHorizontalCard
import com.example.kenanganbakery.presentation.viewmodel.BranchViewModel
import com.example.kenanganbakery.presentation.viewmodel.MenuViewModel
import com.example.kenanganbakery.presentation.viewmodel.OrderViewModel
import com.example.kenanganbakery.presentation.viewmodel.TypeViewModel


data class CartItem(
    val menu: Menu,
    var quantity: Int
)


@Composable
fun MenuScreen(
    branchViewModel: BranchViewModel,
    menuViewModel:MenuViewModel,
    orderViewModel:OrderViewModel,
    typeViewModel:TypeViewModel
) {
    val branchs by branchViewModel.branchs.collectAsState()
    val menus by menuViewModel.menus.collectAsState()
    val types by typeViewModel.types.collectAsState()

    var selectedTypeId by remember { mutableStateOf(1) }
    var deliveryMode by remember { mutableStateOf("Pick up") }
    var selectedBranch by remember { mutableStateOf<Branch?>(null) }
    var showBranchDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Menu?>(null) }
    var cartItems by remember { mutableStateOf<List<CartItem>>(emptyList()) }
    var showCart by remember { mutableStateOf(false) }
    var showClearCartDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        branchViewModel.getAllBranch()
        menuViewModel.getAllMenu()
        typeViewModel.getAllType()
    }

    // Set default branch when data is loaded
    LaunchedEffect(branchs) {
        if (selectedBranch == null && branchs.isNotEmpty()) {
            selectedBranch = branchs[0]
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EFE7))
    ) {
        // Show loading or empty state
        when {
            branchs.isEmpty() || menus.isEmpty() || types.isEmpty() -> {
                LoadingOrEmptyState()
            }
            else -> {
                HomeScreen(
                    deliveryMode = deliveryMode,
                    onDeliveryModeChange = { deliveryMode = it },
                    selectedBranch = selectedBranch,
                    types = types,
                    menus = menus,
                    onBranchClick = { showBranchDialog = true },
                    selectedTypeId = selectedTypeId,
                    onCategoryChange = { selectedTypeId = it },
                    onProductClick = { selectedProduct = it },
                    cartItems = cartItems,
                    onCartClick = { showCart = true }
                )
            }
        }
    }

    // Branch Selection Dialog
    if (showBranchDialog && branchs.isNotEmpty()) {
        BranchSelectionDialog(
            branches = branchs,
            selectedBranch = selectedBranch,
            onBranchSelected = {
                selectedBranch = it
                showBranchDialog = false
            },
            onDismiss = { showBranchDialog = false }
        )
    }

    // Product Detail Dialog
    selectedProduct?.let { product ->
        ProductDetailDialog(
            product = product,
            onDismiss = { selectedProduct = null },
            onAddToCart = {
                val existingItem = cartItems.find { it.menu.id == product.id }
                if (existingItem != null) {
                    cartItems = cartItems.map {
                        if (it.menu.id == product.id) it.copy(quantity = it.quantity + 1)
                        else it
                    }
                } else {
                    cartItems = cartItems + CartItem(product, 1)
                }
                selectedProduct = null
            }
        )
    }

    // Cart Dialog
    if (showCart) {
        CartDialog(
            cartItems = cartItems,
            onDismiss = { showCart = false },
            onQuantityChange = { productId, delta ->
                cartItems = cartItems.map {
                    if (it.menu.id == productId) {
                        val newQuantity = (it.quantity + delta).coerceAtLeast(1)
                        it.copy(quantity = newQuantity)
                    } else it
                }
            },
            onClearCart = { showClearCartDialog = true },
            onBuy = {
                // Handle buy action
                showCart = false
            }
        )
    }

    // Clear Cart Confirmation Dialog
    if (showClearCartDialog) {
        AlertDialog(
            onDismissRequest = { showClearCartDialog = false },
            containerColor = Color.White,
            title = {
                Text(
                    "Apakah anda yakin ingin menghapus semua produk?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    color = Color(0xFF3D2518)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        cartItems = emptyList()
                        showClearCartDialog = false
                        showCart = false
                    }
                ) {
                    Text("Yes", color = Color(0xFF6B4E3D))
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearCartDialog = false }) {
                    Text("No", color = Color(0xFF6B4E3D))
                }
            }
        )
    }
}

@Composable
fun LoadingOrEmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFF6B4E3D),
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Memuat data...",
                fontSize = 16.sp,
                color = Color(0xFF3D2518)
            )
        }
    }
}

@Composable
fun HomeScreen(
    deliveryMode: String,
    onDeliveryModeChange: (String) -> Unit,
    selectedBranch: Branch?,
    types:List<Type>,
    menus:List<Menu>,
    onBranchClick: () -> Unit,
    selectedTypeId: Int,
    onCategoryChange: (Int) -> Unit,
    onProductClick: (Menu) -> Unit,
    cartItems: List<CartItem>,
    onCartClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item {
            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Delivery Mode Tabs
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(24.dp))
                        .padding(4.dp)
                ) {
                    DeliveryTab("Pick up", deliveryMode == "Pick up") {
                        onDeliveryModeChange("Pick up")
                    }
                    DeliveryTab("Delivery", deliveryMode == "Delivery") {
                        onDeliveryModeChange("Delivery")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Branch Selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBranchClick() }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFF6B4E3D),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = selectedBranch?.name ?: "Pilih Toko",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF3D2518)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Arrow",
                        tint = Color(0xFF6B4E3D),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFE0D5C7),
                        unfocusedBorderColor = Color(0xFFE0D5C7)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Category Tabs
                if (types.isNotEmpty()) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(types) { type ->
                            CategoryChip(
                                text = type.type_name,
                                selected = selectedTypeId == type.id,
                                onClick = { onCategoryChange(type.id) }
                            )
                        }
                    }
                }
            }
        }

        // Rekomendasi Produk Section
        val recommendedProducts = menus.filter { it.type_id == 1 }.take(3)

        if (recommendedProducts.isNotEmpty()) {
            item {
                Text(
                    text = "Rekomendasi Produk",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D2518),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(recommendedProducts) { product ->
                        ProductCard(product, onProductClick)
                    }
                }
            }
        }

        // New Produk Section
        item {
            Text(
                text = "Produk",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3D2518),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        // Products Grid
        val filteredProducts = menus.filter { it.type_id == selectedTypeId }

        if (filteredProducts.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Tidak ada produk",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        } else {
            items(filteredProducts) { productPair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductHorizontalCard(productPair, onProductClick)
                    }
                }
            }
        }
    }

    // Floating Cart Button
    if (cartItems.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = onCartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = 64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6B4E3D)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Rp ${cartItems.sumOf { it.menu.price * it.quantity }.formatPrice()}")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Buy")
            }
        }
    }
}






fun Int.formatPrice(): String {
    return "%,d".format(this).replace(',', '.')
}

fun Double.formatPrice(): String {
    return "%,.0f".format(this).replace(',', '.')
}
