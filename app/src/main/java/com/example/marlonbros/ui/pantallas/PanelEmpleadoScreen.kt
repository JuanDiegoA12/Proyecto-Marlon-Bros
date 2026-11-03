package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.datos.DatosDemo
import com.example.marlonbros.datos.Tarea
import com.example.marlonbros.ui.componentes.CampoBusqueda
import com.example.marlonbros.ui.componentes.DestinoNav
import com.example.marlonbros.ui.componentes.EtiquetaNeon
import com.example.marlonbros.ui.componentes.IconoConInsignia
import com.example.marlonbros.ui.componentes.ItemMenu
import com.example.marlonbros.ui.componentes.MarcoPanel
import com.example.marlonbros.ui.componentes.SeccionPendiente
import com.example.marlonbros.ui.componentes.TarjetaPanel
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.Lila
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario
import com.example.marlonbros.ui.theme.VioletaIntenso

private const val TAB_TAREAS = 0
private const val TAB_ESCANER = 2

/**
 * Panel del empleado de bodega (mockup "Tareas Pendientes").
 * Pestañas: Tareas, Inventario, Escáner, Soporte. Solo Tareas tiene diseño por ahora.
 * El menú lateral enlaza con el Dashboard general (se separará por rol cuando exista el backend).
 */
@Composable
fun PanelEmpleadoScreen(
    onIrDashboard: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    // El estado vive aquí para no perderse al cambiar de pestaña.
    var tab by rememberSaveable { mutableIntStateOf(TAB_TAREAS) }
    var busqueda by rememberSaveable { mutableStateOf("") }
    var completadas by rememberSaveable { mutableStateOf(listOf<Int>()) }

    val destinos = remember {
        listOf(
            DestinoNav("Tareas", IconosMarlon.ListaAlt),
            DestinoNav("Inventario", IconosMarlon.Inventario),
            DestinoNav("Escáner", IconosMarlon.Escaner),
            DestinoNav("Soporte", IconosMarlon.Auricular)
        )
    }
    val pendientes = DatosDemo.tareas.filter { it.id !in completadas }

    MarcoPanel(
        destinos = destinos,
        seleccionado = tab,
        onSeleccionar = { tab = it },
        itemsMenu = listOf(
            ItemMenu("Tareas", IconosMarlon.ListaAlt) { tab = TAB_TAREAS },
            ItemMenu("Dashboard general", IconosMarlon.Tablero, onIrDashboard),
            ItemMenu("Cerrar sesión", IconosMarlon.Salir, onCerrarSesion)
        ),
        accionSuperior = {
            IconoConInsignia(IconosMarlon.Campana, "Notificaciones", pendientes.size) {}
        }
    ) { ampliado ->
        if (tab == TAB_TAREAS) {
            TareasContenido(
                ampliado = ampliado,
                busqueda = busqueda,
                onBusqueda = { busqueda = it },
                pendientes = pendientes,
                onCompletar = { completadas = completadas + it },
                onEscanear = { tab = TAB_ESCANER }
            )
        } else {
            SeccionPendiente(destinos[tab].etiqueta, destinos[tab].icono)
        }
    }
}

@Composable
private fun TareasContenido(
    ampliado: Boolean,
    busqueda: String,
    onBusqueda: (String) -> Unit,
    pendientes: List<Tarea>,
    onCompletar: (Int) -> Unit,
    onEscanear: () -> Unit
) {
    val visibles = pendientes.filter {
        busqueda.isBlank() ||
                it.titulo.contains(busqueda, ignoreCase = true) ||
                it.detalle.contains(busqueda, ignoreCase = true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CampoBusqueda(
            valor = busqueda,
            onValorChange = onBusqueda,
            marcador = "Buscar por código o nombre de producto…"
        )
        if (ampliado) {
            Row(
                Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TarjetaEscanear(Modifier.weight(1f).fillMaxHeight(), onEscanear)
                TarjetaTareas(Modifier.weight(1.6f), visibles, onCompletar)
            }
        } else {
            TarjetaEscanear(Modifier.fillMaxWidth(), onEscanear)
            TarjetaTareas(Modifier.fillMaxWidth(), visibles, onCompletar)
        }
    }
}

@Composable
private fun TarjetaEscanear(modifier: Modifier, onEscanear: () -> Unit) {
    val forma = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .clip(forma)
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF0E3B47), Color(0xFF1B1F2E), Color(0xFF2A1740))
                )
            )
            .border(1.dp, Cian.copy(alpha = 0.45f), forma)
            .clickable(role = Role.Button, onClick = onEscanear)
            .padding(vertical = 36.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier.size(76.dp).clip(CircleShape).background(Cian.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(IconosMarlon.Escaner, null, tint = Cian, modifier = Modifier.size(38.dp))
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Escanear Pedido",
            color = Cian,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(Cian, blurRadius = 14f)
            )
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "ACCESO RÁPIDO",
            color = TextoClaro.copy(alpha = 0.8f),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp)
        )
    }
}

@Composable
private fun TarjetaTareas(modifier: Modifier, tareas: List<Tarea>, onCompletar: (Int) -> Unit) {
    TarjetaPanel(modifier) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(IconosMarlon.ListaAlt, null, tint = Lila, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                text = "Tareas Pendientes",
                color = TextoClaro,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )
            EtiquetaNeon(
                texto = "${tareas.size} TAREAS",
                colorTexto = TextoClaro,
                colorFondo = BordeTarjeta
            )
        }
        Spacer(Modifier.height(14.dp))
        if (tareas.isEmpty()) {
            Text(
                text = "No hay tareas pendientes",
                color = TextoSecundario,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            tareas.forEach { FilaTarea(it, onCompletar) }
        }
    }
}

@Composable
private fun FilaTarea(tarea: Tarea, onCompletar: (Int) -> Unit) {
    val acento = if (tarea.esStock) Lila else Cian
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(FondoCampo)
            .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (tarea.esStock) VioletaIntenso.copy(alpha = 0.25f) else Cian.copy(alpha = 0.12f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (tarea.esStock) IconosMarlon.Sincronizar else IconosMarlon.Inventario,
                null,
                tint = acento,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = tarea.titulo,
                color = if (tarea.esStock) Lila else TextoClaro,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = tarea.detalle,
                color = TextoSecundario,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
        IconButton(onClick = { onCompletar(tarea.id) }) {
            Icon(
                IconosMarlon.CheckCirculo,
                contentDescription = "Marcar como hecha: ${tarea.titulo}",
                tint = TextoSecundario
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PanelEmpleadoPreview() {
    MarlonBrosTheme { PanelEmpleadoScreen() }
}

@Preview(widthDp = 900, heightDp = 500)
@Composable
private fun PanelEmpleadoAnchoPreview() {
    MarlonBrosTheme { PanelEmpleadoScreen() }
}
