package com.example.kenanganbakery.presentation.ui.screen.splash.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onClick:()-> Unit) {
    Box(modifier.fillMaxSize()){
        Column(
            Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            ModernText(
                text = "Kenangan Bakery",
                size = 18,
                weight = FontWeight.Bold
            )

            ModernText(
                text = "Setiap Rasa Memiliki Kenangan",
                size = 12,
                weight = FontWeight.Normal
            )

            Spacer(Modifier.weight(1f))

            ModernButton(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                text = "Get Started",
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}