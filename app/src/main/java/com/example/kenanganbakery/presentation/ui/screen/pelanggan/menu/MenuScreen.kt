package com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


data class CartItem(
    val product: Product,
    var quantity: Int
)


@Composable
fun MenuScreen(

) {
    var selectedCategory by remember { mutableStateOf("pastries") }
    var deliveryMode by remember { mutableStateOf("Pick up") }
    var selectedBranch by remember { mutableStateOf(DummyData.branches[0]) }
    var showBranchDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var cartItems by remember { mutableStateOf<List<CartItem>>(emptyList()) }
    var showCart by remember { mutableStateOf(false) }
    var showClearCartDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EFE7))
    ) {
        HomeScreen(
            deliveryMode = deliveryMode,
            onDeliveryModeChange = { deliveryMode = it },
            selectedBranch = selectedBranch,
            onBranchClick = { showBranchDialog = true },
            selectedCategory = selectedCategory,
            onCategoryChange = { selectedCategory = it },
            onProductClick = { selectedProduct = it },
            cartItems = cartItems,
            onCartClick = { showCart = true }
        )
    }

    // Branch Selection Dialog
    if (showBranchDialog) {
        BranchSelectionDialog(
            branches = DummyData.branches,
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
                val existingItem = cartItems.find { it.product.id == product.id }
                if (existingItem != null) {
                    cartItems = cartItems.map {
                        if (it.product.id == product.id) it.copy(quantity = it.quantity + 1)
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
                    if (it.product.id == productId) {
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
fun HomeScreen(
    deliveryMode: String,
    onDeliveryModeChange: (String) -> Unit,
    selectedBranch: Branch,
    onBranchClick: () -> Unit,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    onProductClick: (Product) -> Unit,
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
                        text = "Pilih Toko",
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
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOf("pastries", "cakes", "bread", "cookies")) { category ->
                        CategoryChip(
                            text = category,
                            selected = selectedCategory == category,
                            onClick = { onCategoryChange(category) }
                        )
                    }
                }
            }
        }

        // Rekomendasi Produk Section
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
                val recommendedProducts = DummyData.products.filter {
                    it.category == "cakes"
                }.take(3)

                items(recommendedProducts) { product ->
                    ProductCard(product, onProductClick)
                }
            }
        }

        // New Produk Section
        item {
            Text(
                text = "New Produk",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3D2518),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        // Products Grid
        val filteredProducts = DummyData.products.filter { it.category == selectedCategory }
        items(filteredProducts.chunked(2)) { productPair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                productPair.forEach { product ->
                    Box(modifier = Modifier.weight(1f)) {
                        ProductCard(product, onProductClick)
                    }
                }
                if (productPair.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
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
                Text("Rp ${cartItems.sumOf { it.product.price * it.quantity }.formatPrice()}")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Buy")
            }
        }
    }
}

@Composable
fun RowScope.DeliveryTab(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) Color(0xFFF5EFE7) else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) Color(0xFF3D2518) else Color.Gray
        )
    }
}

@Composable
fun CategoryChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) Color(0xFF6B4E3D) else Color(0xFFE8E0D5))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color(0xFF6B4E3D),
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick(product) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = product.image,
                fontSize = 60.sp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3D2518),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rp ${product.price.formatPrice()}",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3D2518),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BranchSelectionDialog(
    branches: List<Branch>,
    selectedBranch: Branch,
    onBranchSelected: (Branch) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Pilih Toko",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D2518)
                )
                Spacer(modifier = Modifier.height(16.dp))
                branches.forEach { branch ->
                    BranchItem(
                        branch = branch,
                        isSelected = branch.id == selectedBranch.id,
                        onClick = { onBranchSelected(branch) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun BranchItem(branch: Branch, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFF5EFE7) else Color.White)
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = Color(0xFF6B4E3D)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = branch.name,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3D2518)
            )
            Text(
                text = "${branch.address}, ${branch.city}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF6B4E3D)
            )
        }
    }
}

@Composable
fun ProductDetailDialog(
    product: Product,
    onDismiss: () -> Unit,
    onAddToCart: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                Column {
                    // Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back",
                                tint = Color(0xFF3D2518)
                            )
                        }
                        Text(
                            text = "Detail Produk",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Color(0xFF3D2518)
                        )
                    }

                    // Product Image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFFF5EFE7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = product.image, fontSize = 100.sp)
                    }

                    // Product Info
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = product.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3D2518)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Rp ${product.price.formatPrice()}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF3D2518)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = product.description,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Berat: ±${product.weight} gram",
                            fontSize = 14.sp,
                            color = Color(0xFF3D2518)
                        )
                        if (product.ingredients.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Komposisi: ${product.ingredients}",
                                fontSize = 14.sp,
                                color = Color(0xFF3D2518),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                // Add Button
                FloatingActionButton(
                    onClick = onAddToCart,
                    containerColor = Color(0xFF6B4E3D),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CartDialog(
    cartItems: List<CartItem>,
    onDismiss: () -> Unit,
    onQuantityChange: (Int, Int) -> Unit,
    onClearCart: () -> Unit,
    onBuy: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Items (${cartItems.size})",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF3D2518)
                    )
                    TextButton(onClick = onClearCart) {
                        Text("Clear all", color = Color(0xFF6B4E3D))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                cartItems.forEach { cartItem ->
                    CartItemRow(
                        cartItem = cartItem,
                        onQuantityChange = onQuantityChange
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Divider(color = Color(0xFFE0D5C7))

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            tint = Color(0xFF6B4E3D)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rp ${cartItems.sumOf { it.product.price * it.quantity }.formatPrice()}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3D2518)
                        )
                    }
                    Button(
                        onClick = onBuy,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6B4E3D)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Buy")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem, onQuantityChange: (Int, Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFF5EFE7), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = cartItem.product.image, fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = cartItem.product.name,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3D2518)
                )
                Text(
                    text = "Rp ${cartItem.product.price.formatPrice()}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onQuantityChange(cartItem.product.id, -1) },
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFFF5EFE7), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Decrease",
                    tint = Color(0xFF6B4E3D),
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = "${cartItem.quantity}",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3D2518)
            )
            IconButton(
                onClick = { onQuantityChange(cartItem.product.id, 1) },
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF6B4E3D), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

fun Int.formatPrice(): String {
    return "%,d".format(this).replace(',', '.')
}