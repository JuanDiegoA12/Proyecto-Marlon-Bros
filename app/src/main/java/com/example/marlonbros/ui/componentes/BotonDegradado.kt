package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoBotonDegradado
import com.example.marlonbros.ui.theme.VioletaIntenso

/** Botón principal con degradado cian → violeta y resplandor. */
@Composable
fun BotonDegradado(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(10.dp)
    val degradado = Brush.horizontalGradient(listOf(Cian, VioletaIntenso))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .dropShadow(forma, Shadow(radius = 18.dp, brush = degradado, alpha = 0.45f))
            .clip(forma)
            .background(degradado)
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = TextoBotonDegradado,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                letterSpacing = 1.sp
            )
        )
    }
}

@Preview(backgroundColor = 0xFF14151D, showBackground = true)
@Composable
private fun BotonDegradadoPreview() {
    MarlonBrosTheme {
        BotonDegradado("CONECTAR", onClick = {}, modifier = Modifier.padding(24.dp))
    }
}
