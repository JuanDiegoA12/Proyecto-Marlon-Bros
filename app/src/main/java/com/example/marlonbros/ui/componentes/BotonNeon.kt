package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.RellenoBoton
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.VerdeAzulado

/** Botón de ancho completo con borde y resplandor de color. */
@Composable
fun BotonNeon(
    texto: String,
    icono: ImageVector,
    colorBorde: Color,
    colorTexto: Color,
    colorIcono: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(16.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .dropShadow(forma, Shadow(radius = 16.dp, color = colorBorde.copy(alpha = 0.45f)))
            .clip(forma)
            .background(RellenoBoton)
            .border(1.5.dp, colorBorde.copy(alpha = 0.8f), forma)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = colorIcono,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(text = texto, color = colorTexto, style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(backgroundColor = 0xFF14151D, showBackground = true)
@Composable
private fun BotonNeonPreview() {
    MarlonBrosTheme {
        BotonNeon(
            texto = "Soy Empleado",
            icono = IconosMarlon.Gafete,
            colorBorde = VerdeAzulado,
            colorTexto = TextoClaro,
            colorIcono = Cian,
            onClick = {},
            modifier = Modifier.padding(24.dp)
        )
    }
}
