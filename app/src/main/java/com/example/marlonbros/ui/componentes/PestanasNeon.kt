package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoPestanaActiva
import com.example.marlonbros.ui.theme.TextoClaro

/** Fila de pestañas; la activa lleva fondo más oscuro, texto cian y subrayado. */
@Composable
fun PestanasNeon(
    pestanas: List<String>,
    seleccionada: Int,
    onSeleccionar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().selectableGroup()) {
            pestanas.forEachIndexed { indice, texto ->
                val activa = indice == seleccionada
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (activa) FondoPestanaActiva else Color.Transparent)
                        .semantics { selected = activa }
                        .clickable(role = Role.Tab) { onSeleccionar(indice) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = texto,
                            color = if (activa) Cian else TextoClaro.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                shadow = if (activa) Shadow(Cian, blurRadius = 16f) else null
                            )
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(if (activa) Cian else BordeTarjeta)
                    )
                }
            }
        }
    }
}
