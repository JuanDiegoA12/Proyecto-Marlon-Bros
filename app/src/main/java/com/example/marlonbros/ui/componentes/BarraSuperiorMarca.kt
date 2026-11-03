package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.marlonbros.R
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.Violeta


@Composable
fun BarraSuperiorMarca(
    modifier: Modifier = Modifier,
    onMenu: (() -> Unit)? = null,
    centro: @Composable RowScope.() -> Unit = {},
    accion: @Composable RowScope.() -> Unit = {}
) {
    Column(modifier.fillMaxWidth().background(FondoTarjeta)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onMenu != null) {
                IconButton(onClick = onMenu) {
                    Icon(IconosMarlon.Menu, contentDescription = "Abrir menú", tint = TextoClaro)
                }
            } else {
                Spacer(Modifier.width(8.dp))
            }
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Marlon Bros",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(36.dp).clip(CircleShape)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "MARLON BROS",
                color = Cian,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(Cian, blurRadius = 16f)
                )
            )
            centro()
            Spacer(Modifier.weight(1f))
            accion()
        }
        HorizontalDivider(color = BordeTarjeta)
    }
}

/** Botón de ícono con insignia numérica (campana, carrito). La insignia se oculta si [cantidad] es 0. */
@Composable
fun IconoConInsignia(
    icono: ImageVector,
    descripcion: String,
    cantidad: Int,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        BadgedBox(
            badge = {
                if (cantidad > 0) {
                    Badge(containerColor = Violeta, contentColor = Color.White) {
                        Text(cantidad.toString())
                    }
                }
            }
        ) {
            Icon(icono, contentDescription = descripcion, tint = TextoClaro)
        }
    }
}