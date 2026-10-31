package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.TextoClaro

/** Selector de una opción con etiqueta, en el mismo estilo que [CampoNeon]. */
@Composable
fun SelectorNeon(
    etiqueta: String,
    opciones: List<String>,
    seleccion: String?,
    onSeleccion: (String) -> Unit,
    marcador: String,
    modifier: Modifier = Modifier,
    iconoEtiqueta: ImageVector? = null
) {
    var abierto by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        EtiquetaCampo(etiqueta, iconoEtiqueta)
        Spacer(Modifier.height(10.dp))
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(FondoCampo, FormaCampo)
                    .border(
                        1.dp,
                        if (abierto) Cian.copy(alpha = 0.6f) else Color.Transparent,
                        FormaCampo
                    )
                    .clickable(role = Role.DropdownList) { abierto = true }
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = seleccion ?: marcador,
                    color = TextoClaro,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = IconosMarlon.FlechaAbajo,
                    contentDescription = null,
                    tint = TextoClaro.copy(alpha = 0.85f),
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(if (abierto) 180f else 0f)
                )
            }
            DropdownMenu(
                expanded = abierto,
                onDismissRequest = { abierto = false },
                containerColor = FondoTarjeta
            ) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = opcion,
                                color = if (opcion == seleccion) Cian else TextoClaro,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = {
                            onSeleccion(opcion)
                            abierto = false
                        },
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}
