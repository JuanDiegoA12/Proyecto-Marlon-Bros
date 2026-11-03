package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.datos.DatosDemo
import com.example.marlonbros.datos.EstadoPedido
import com.example.marlonbros.datos.Pedido
import com.example.marlonbros.ui.componentes.BotonCompacto
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
import com.example.marlonbros.ui.theme.RojoAlerta
import com.example.marlonbros.ui.theme.RojoAlertaFondo
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario
import com.example.marlonbros.ui.theme.VerdeExito
import com.example.marlonbros.ui.theme.VerdeExitoFondo
import com.example.marlonbros.ui.theme.VioletaIntenso

private const val TAB_DASHBOARD = 0
private const val TAB_INVENTARIO = 1

/**
 * Dashboard general (mockup "Dashboard General").
 * Pestañas: Dashboard, Inventario, Reportes, Perfil. Solo Dashboard tiene diseño por ahora.
 */
@Composable
fun DashboardScreen(
    onIrTareas: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    var tab by rememberSaveable { mutableIntStateOf(TAB_DASHBOARD) }
    val destinos = remember {
        listOf(
            DestinoNav("Dashboard", IconosMarlon.Tablero),
            DestinoNav("Inventario", IconosMarlon.Inventario),
            DestinoNav("Reportes", IconosMarlon.Grafica),
            DestinoNav("Perfil", IconosMarlon.Persona)
        )
    }
    MarcoPanel(
        destinos = destinos,
        seleccionado = tab,
        onSeleccionar = { tab = it },
        itemsMenu = listOf(
            ItemMenu("Dashboard", IconosMarlon.Tablero) { tab = TAB_DASHBOARD },
            ItemMenu("Tareas de bodega", IconosMarlon.ListaAlt, onIrTareas),
            ItemMenu("Cerrar sesión", IconosMarlon.Salir, onCerrarSesion)
        ),
        accionSuperior = {
            IconoConInsignia(IconosMarlon.Campana, "Notificaciones", 0) {}
        }
    ) { ampliado ->
        if (tab == TAB_DASHBOARD) {
            DashboardContenido(ampliado, onAnadirJuego = { tab = TAB_INVENTARIO })
        } else {
            SeccionPendiente(destinos[tab].etiqueta, destinos[tab].icono)
        }
    }
}

@Composable
private fun DashboardContenido(ampliado: Boolean, onAnadirJuego: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Dashboard General",
                    color = TextoClaro,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Resumen de operaciones y ventas",
                    color = TextoSecundario,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (ampliado) {
                BotonCompacto("Añadir Juego", onAnadirJuego, icono = IconosMarlon.MasCirculo)
            }
        }
        if (!ampliado) {
            BotonCompacto("Añadir Juego", onAnadirJuego, icono = IconosMarlon.MasCirculo)
        }

        if (ampliado) {
            Row(
                Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TarjetaVentasDia(Modifier.weight(1f).fillMaxHeight())
                TarjetaUsuariosNuevos(Modifier.weight(1f).fillMaxHeight())
                TarjetaStockCritico(Modifier.weight(1f).fillMaxHeight())
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TarjetaRendimiento(Modifier.weight(2f))
                TarjetaPedidos(Modifier.weight(1f))
            }
        } else {
            TarjetaVentasDia(Modifier.fillMaxWidth())
            TarjetaUsuariosNuevos(Modifier.fillMaxWidth())
            TarjetaStockCritico(Modifier.fillMaxWidth())
            TarjetaRendimiento(Modifier.fillMaxWidth())
            TarjetaPedidos(Modifier.fillMaxWidth())
        }
    }
}

// --- Tarjetas de métricas -------------------------------------------------------------------

@Composable
private fun TarjetaMetrica(
    titulo: String,
    valor: String,
    icono: ImageVector,
    colorIcono: Color,
    fondoIcono: Color,
    modifier: Modifier,
    pie: @Composable ColumnScope.() -> Unit
) {
    TarjetaPanel(modifier) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Text(
                text = titulo,
                color = TextoSecundario,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier.weight(1f)
            )
            Box(
                Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(fondoIcono),
                contentAlignment = Alignment.Center
            ) {
                Icon(icono, null, tint = colorIcono, modifier = Modifier.size(22.dp))
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = valor,
            color = TextoClaro,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(8.dp))
        pie()
    }
}

@Composable
private fun Variacion(texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(IconosMarlon.Tendencia, null, tint = VerdeExito, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(4.dp))
        Text(
            text = texto,
            color = VerdeExito,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
private fun TarjetaVentasDia(modifier: Modifier) =
    TarjetaMetrica(
        titulo = "Ventas del Día",
        valor = "\$4'250.000 COP",
        icono = IconosMarlon.Dinero,
        colorIcono = Cian,
        fondoIcono = Cian.copy(alpha = 0.12f),
        modifier = modifier
    ) { Variacion("+15% vs ayer") }

@Composable
private fun TarjetaUsuariosNuevos(modifier: Modifier) =
    TarjetaMetrica(
        titulo = "Usuarios Nuevos",
        valor = "124",
        icono = IconosMarlon.PersonaMas,
        colorIcono = Lila,
        fondoIcono = VioletaIntenso.copy(alpha = 0.25f),
        modifier = modifier
    ) { Variacion("+5% vs semana pasada") }

@Composable
private fun TarjetaStockCritico(modifier: Modifier) =
    TarjetaMetrica(
        titulo = "Stock Crítico",
        valor = "8 Títulos",
        icono = IconosMarlon.Alerta,
        colorIcono = RojoAlerta,
        fondoIcono = RojoAlertaFondo,
        modifier = modifier
    ) {
        BarraSegmentada(total = 8, llenos = 6)
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Requieren reposición inmediata",
            color = TextoSecundario,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }

@Composable
private fun BarraSegmentada(total: Int, llenos: Int) {
    Row(
        Modifier.fillMaxWidth().height(6.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(total) { i ->
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(3.dp))
                    .background(if (i < llenos) RojoAlerta else BordeTarjeta)
            )
        }
    }
}

// --- Rendimiento de ventas ------------------------------------------------------------------

@Composable
private fun TarjetaRendimiento(modifier: Modifier) {
    TarjetaPanel(modifier) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Rendimiento de Ventas",
                color = TextoClaro,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )
            EtiquetaNeon("Últimos 7 días", TextoClaro, FondoCampo)
        }
        Spacer(Modifier.height(16.dp))
        GraficaBarras(DatosDemo.diasSemana, DatosDemo.ventasSemana)
    }
}

/** Barras verticales (valores entre 0 y 1) con degradado cian → violeta. */
@Composable
private fun GraficaBarras(etiquetas: List<String>, valores: List<Float>) {
    Row(
        Modifier.fillMaxWidth().height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        etiquetas.forEachIndexed { i, dia ->
            Column(Modifier.weight(1f).fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                    Box(
                        Modifier
                            .fillMaxWidth(0.6f)
                            .fillMaxHeight(valores[i].coerceIn(0.05f, 1f))
                            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                            .background(Brush.verticalGradient(listOf(Cian, VioletaIntenso)))
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    text = dia,
                    color = TextoSecundario,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

// --- Pedidos recientes ----------------------------------------------------------------------

@Composable
private fun TarjetaPedidos(modifier: Modifier) {
    TarjetaPanel(modifier) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Pedidos Recientes",
                color = TextoClaro,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Ver todos",
                color = Cian,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp)
            )
        }
        Spacer(Modifier.height(14.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            DatosDemo.pedidosRecientes.forEach { FilaPedido(it) }
        }
    }
}

@Composable
private fun FilaPedido(pedido: Pedido) {
    val (colorEstado, fondoEstado) = when (pedido.estado) {
        EstadoPedido.COMPLETADO -> VerdeExito to VerdeExitoFondo
        EstadoPedido.EN_TRANSITO -> Lila to VioletaIntenso.copy(alpha = 0.25f)
        EstadoPedido.PENDIENTE -> TextoClaro to BordeTarjeta
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(FondoCampo)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(BordeTarjeta),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (pedido.esAccesorio) IconosMarlon.Auricular else IconosMarlon.Gamepad,
                null,
                tint = TextoClaro,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = pedido.codigo,
                color = TextoClaro,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = pedido.producto,
                color = TextoSecundario,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
        Spacer(Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.End) {
            EtiquetaNeon(pedido.estado.etiqueta, colorEstado, fondoEstado)
            Spacer(Modifier.height(4.dp))
            Text(
                text = pedido.valor,
                color = TextoSecundario,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DashboardPreview() {
    MarlonBrosTheme { DashboardScreen() }
}

@Preview(widthDp = 1000, heightDp = 700)
@Composable
private fun DashboardAnchoPreview() {
    MarlonBrosTheme { DashboardScreen() }
}