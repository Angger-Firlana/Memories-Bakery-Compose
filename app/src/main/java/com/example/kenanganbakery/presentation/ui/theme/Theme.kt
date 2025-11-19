package com.example.kenanganbakery.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BrownLight,              // Tombol utama: coklat muda
    onPrimary = BrownText,             // Teks di tombol: coklat gelap

    primaryContainer = BrownPrimary,   // Container tab
    onPrimaryContainer = Color.White,

    background = Color(0xFF2B1E18),    // Background gelap ala bakery
    onBackground = CreamBackground,    // Teks cream

    surface = Color(0xFF3A2922),       // Card / textfield background
    onSurface = CreamBackground,

    outline = Color(0xFF60483D),       // Border tipis coklat gelap

    secondary = BrownLight,
    onSecondary = BrownText,

    tertiary = BrownPrimary
)


private val LightColorScheme = lightColorScheme(
    primary = BrownPrimary,
    onPrimary = Color.White,
    primaryContainer = BrownLight,
    onPrimaryContainer = BrownText,

    background = CreamBackground,
    onBackground = BrownText,

    surface = SurfaceWhite,
    onSurface = BrownText,

    outline = OutlineLight,

    secondary = BrownLight,
    tertiary = BrownPrimary
)


@Composable
fun KenanganBakeryTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}