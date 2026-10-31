package com.example.marlonbros.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// La app siempre usa tema oscuro; sin colores dinámicos para respetar la paleta.
private val MarlonColorScheme = darkColorScheme(
    primary = Cian,
    secondary = Violeta,
    tertiary = VerdeAzulado,
    background = FondoOscuro,
    surface = RellenoBoton,
    onPrimary = FondoOscuro,
    onSecondary = FondoOscuro,
    onBackground = TextoClaro,
    onSurface = TextoClaro
)

@Composable
fun MarlonBrosTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MarlonColorScheme,
        typography = Typography,
        content = content
    )
}
