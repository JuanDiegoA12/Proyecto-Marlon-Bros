package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.marlonbros.R
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import kotlinx.coroutines.launch

data class ItemMenu(val etiqueta: String, val icono: ImageVector, val onClick: () -> Unit)

private const val ANCHO_AMPLIADO_DP = 600

@Composable
fun MarcoPanel(
    destinos: List<DestinoNav>,
    seleccionado: Int,
    onSeleccionar: (Int) -> Unit,
    itemsMenu: List<ItemMenu>,
    modifier: Modifier = Modifier,
    accionSuperior: @Composable RowScope.() -> Unit = {},
    contenido: @Composable (ampliado: Boolean) -> Unit
) {
    val estadoMenu = rememberDrawerState(DrawerValue.Closed)
    val alcance = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = estadoMenu,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = FondoTarjeta) {
                Row(
                    Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo Marlon Bros",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "MARLON BROS",
                        color = Cian,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(Cian, blurRadius = 16f)
                        )
                    )
                }
                itemsMenu.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item.etiqueta) },
                        icon = { Icon(item.icono, contentDescription = null) },
                        selected = false,
                        onClick = {
                            alcance.launch { estadoMenu.close() }
                            item.onClick()
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        FondoCuadricula {
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val ampliado = maxWidth >= ANCHO_AMPLIADO_DP.dp
                Column(Modifier.fillMaxSize().statusBarsPadding()) {
                    BarraSuperiorMarca(
                        onMenu = { alcance.launch { estadoMenu.open() } },
                        centro = {
                            if (ampliado) {
                                Spacer(Modifier.width(24.dp))
                                PestanasSuperioresNeon(destinos, seleccionado, onSeleccionar)
                            }
                        },
                        accion = accionSuperior
                    )
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .then(if (ampliado) Modifier.navigationBarsPadding() else Modifier)
                    ) {
                        contenido(ampliado)
                    }
                    if (!ampliado) {
                        BarraInferiorNeon(destinos, seleccionado, onSeleccionar)
                    }
                }
            }
        }
    }
}