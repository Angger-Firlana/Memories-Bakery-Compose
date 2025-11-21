package com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.domain.models.menu.Menu
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice

@Composable
fun ProductCard(product: Menu, onClick: (Menu) -> Unit) {
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
                text = "🍰",
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
fun ProductHorizontalCard(product: Menu, onClick: (Menu) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(product) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row (
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🍰",
                fontSize = 60.sp,
                modifier = Modifier.padding(16.dp)
            )
            Column {
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
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onClick(product)
                },
                modifier = Modifier.padding(8.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFF3D2518),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(20.dp)
                )
            }


        }
        HorizontalDivider()
    }
}

