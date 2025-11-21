package com.example.kenanganbakery.presentation.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            Modifier.fillMaxSize()
        ) {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(120.dp).clip(CircleShape)
                    )

                    ModernText(
                        text = "Profile",
                        size = 18
                    )
                }
            }
        }
    }
}