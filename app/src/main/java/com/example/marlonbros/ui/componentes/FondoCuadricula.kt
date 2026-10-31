package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.theme.FondoOscuro
import com.example.marlonbros.ui.theme.FondoVioleta
import com.example.marlonbros.ui.theme.LineaCuadricula

/** Fondo oscuro con cuadrícula tenue y un resplandor violeta abajo a la izquierda. */
@Composable
fun FondoCuadricula(
    modifier: Modifier = Modifier,
    espaciado: Dp = 24.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(FondoOscuro)
                drawRect(
                    Brush.radialGradient(
                        colors = listOf(FondoVioleta.copy(alpha = 0.6f), Color.Transparent),
                        center = Offset(0f, size.height),
                        radius = size.maxDimension * 0.7f
                    )
                )
                val paso = espaciado.toPx()
                val grosor = 1.dp.toPx()
                var x = 0f
                while (x <= size.width) {
                    drawLine(LineaCuadricula, Offset(x, 0f), Offset(x, size.height), grosor)
                    x += paso
                }
                var y = 0f
                while (y <= size.height) {
                    drawLine(LineaCuadricula, Offset(0f, y), Offset(size.width, y), grosor)
                    y += paso
                }
            },
        content = content
    )
}
