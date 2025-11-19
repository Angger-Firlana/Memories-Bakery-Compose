package com.example.kenanganbakery.presentation.ui.component.text

import android.util.Size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.kenanganbakery.data.local.font.AppFont

@Composable
fun ModernText(modifier: Modifier = Modifier, text:String, textAlign: TextAlign = TextAlign.Start, size: Int = 14, weight:FontWeight = FontWeight.Normal, color:Color = MaterialTheme.colorScheme.onBackground, lineHeight: Int? = null) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontFamily = AppFont.Poppins,
        fontSize = size.sp,
        fontWeight = weight,
        color = color,
        lineHeight = lineHeight?.sp ?: TextUnit.Unspecified
    )
}