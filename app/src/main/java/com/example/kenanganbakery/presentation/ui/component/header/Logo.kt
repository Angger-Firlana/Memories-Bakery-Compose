package com.example.kenanganbakery.presentation.ui.component.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun LogoKenanganBakery(modifier: Modifier = Modifier, size: Int) {
    Row(
        modifier
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            null,
            modifier = Modifier.size(size.dp)
        )
        Spacer(Modifier.width(6.dp))
        ModernText(
            text = "Kenangan Bakery",
            size = size
        )
    }
}