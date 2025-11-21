package com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.CartItem
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice

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
                Text(text = "🍰", fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = cartItem.menu.name,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3D2518)
                )
                Text(
                    text = "Rp ${cartItem.menu.price.formatPrice()}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { onQuantityChange(cartItem.menu.id, -1) },
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
                onClick = { onQuantityChange(cartItem.menu.id, 1) },
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
