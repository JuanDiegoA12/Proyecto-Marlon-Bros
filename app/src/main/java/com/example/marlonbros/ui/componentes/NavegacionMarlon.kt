package com.example.marlonbros.ui.componentes


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario

/** Un destino de la navegación principal (pestaña inferior o enlace superior). */
data class DestinoNav(val etiqueta: String, val icono: ImageVector)

/** Barra inferior del móvil: ícono + etiqueta; el destino activo va resaltado en cian. */
@Composable
fun BarraInferiorNeon(
    destinos: List<DestinoNav>,
    seleccionado: Int,
    onSeleccionar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth().background(FondoTarjeta)) {
        HorizontalDivider(color = BordeTarjeta)
        Row(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .selectableGroup()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            destinos.forEachIndexed { indice, destino ->
                val activo = indice == seleccionado
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (activo) Cian.copy(alpha = 0.12f) else Color.Transparent)
                        .selectable(
                            selected = activo,
                            role = Role.Tab,
                            onClick = { onSeleccionar(indice) }
                        )
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = destino.icono,
                        contentDescription = null,
                        tint = if (activo) Cian else TextoSecundario,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = destino.etiqueta,
                        color = if (activo) Cian else TextoSecundario,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 11.sp,
                            fontWeight = if (activo) FontWeight.Bold else FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

/** Enlaces de navegación para pantallas anchas (van en la barra superior). */
@Composable
fun PestanasSuperioresNeon(
    destinos: List<DestinoNav>,
    seleccionado: Int,
    onSeleccionar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier.selectableGroup()) {
        destinos.forEachIndexed { indice, destino ->
            val activo = indice == seleccionado
            Column(
                modifier = Modifier
                    .selectable(
                        selected = activo,
                        role = Role.Tab,
                        onClick = { onSeleccionar(indice) }
                    )
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = destino.etiqueta,
                    color = if (activo) Cian else TextoClaro.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = if (activo) FontWeight.Bold else FontWeight.Medium,
                        shadow = if (activo) Shadow(Cian, blurRadius = 12f) else null
                    )
                )
                Spacer(Modifier.height(4.dp))
                Box(
                    Modifier
                        .width(24.dp)
                        .height(2.dp)
                        .background(if (activo) Cian else Color.Transparent)
                )
            }
        }
    }
}
