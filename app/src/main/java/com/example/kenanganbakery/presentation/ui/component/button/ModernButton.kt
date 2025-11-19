package com.example.kenanganbakery.presentation.ui.component.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun ModernButton(modifier: Modifier = Modifier, text:String, onClick:()-> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier.height(65.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        ModernText(
            text = text,
            color = MaterialTheme.colorScheme.background,
            weight = FontWeight.Medium
        )
    }
}