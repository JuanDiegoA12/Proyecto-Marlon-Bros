package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoBotonDegradado
import com.example.marlonbros.ui.theme.TextoBotonOscuro
import com.example.marlonbros.ui.theme.VioletaIntenso

/** Botón principal con degradado cian → violeta y resplandor; ícono opcional. */
@Composable
fun BotonDegradado(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icono: ImageVector? = null,
    colorTexto: Color = TextoBotonDegradado
) {
    val forma = RoundedCornerShape(10.dp)
    val degradado = Brush.horizontalGradient(listOf(Cian, VioletaIntenso))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .dropShadow(forma, Shadow(radius = 18.dp, brush = degradado, alpha = 0.45f))
            .clip(forma)
            .background(degradado)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icono != null) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = colorTexto,
                modifier = Modifier.size(26.dp)
            )
            Spacer(Modifier.width(10.dp))
        }
        Text(
            text = texto,
            color = colorTexto,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                letterSpacing = if (icono == null) 1.sp else 0.sp
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

@Preview(backgroundColor = 0xFF14151D, showBackground = true)
@Composable
private fun BotonDegradadoIconoPreview() {
    MarlonBrosTheme {
        BotonDegradado(
            texto = "Registrar Empleado",
            onClick = {},
            icono = IconosMarlon.Registrar,
            colorTexto = TextoBotonOscuro,
            modifier = Modifier.padding(24.dp)
        )
    }
}
