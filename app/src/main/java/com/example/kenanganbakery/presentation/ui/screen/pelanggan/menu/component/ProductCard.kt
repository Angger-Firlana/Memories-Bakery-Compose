package com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.domain.models.menu.Menu
import com.example.kenanganbakery.presentation.ui.screen.pelanggan.menu.formatPrice
import com.example.kenanganbakery.presentation.viewmodel.MenuViewModel

@Composable
fun ProductCard(product: Menu,menuViewModel: MenuViewModel, onClick: (Menu) -> Unit) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(product) {
        product.photo_url?.let {
            menuViewModel.getMenuImage(
                url = it
            ){ image ->
                bitmap = image
            }
        }
    }
    Card(
        modifier = Modifier
            .clickable { onClick(product) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = bitmap?.asImageBitmap()?.let {
                    BitmapPainter(it)
                }?: painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.padding(8.dp).size(80.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
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
fun ProductHorizontalCard(product: Menu,menuViewModel: MenuViewModel, onClick: (Menu) -> Unit) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(product) {
        product.photo_url?.let {
            menuViewModel.getMenuImage(
                url = it
            ){ image ->
                bitmap = image
            }
        }
    }
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
            Image(
                painter = bitmap?.asImageBitmap()?.let {
                    BitmapPainter(it)
                }?: painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.padding(8.dp).size(80.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
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

