package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.datos.DatosDemo
import com.example.marlonbros.datos.Juego
import com.example.marlonbros.datos.comoPesos
import com.example.marlonbros.ui.componentes.BotonCompacto
import com.example.marlonbros.ui.componentes.BotonContorno
import com.example.marlonbros.ui.componentes.EtiquetaNeon
import com.example.marlonbros.ui.componentes.PortadaJuego
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.Lila
import com.example.marlonbros.ui.theme.RojoAlerta
import com.example.marlonbros.ui.theme.RojoAlertaFondo
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario

private val OpcionesOrden = listOf("Relevancia", "Precio: menor a mayor", "Precio: mayor a menor")

/**
 * Catálogo del cliente (mockup "Catálogo"): destacado, promociones, filtros por categoría,
 * orden y cuadrícula de juegos. Filtros y orden funcionan sobre datos de ejemplo.
 */
@Composable
fun CatalogoContenido(
    ampliado: Boolean,
    categoria: String,
    onCategoria: (String) -> Unit,
    orden: Int,
    onOrden: (Int) -> Unit,
    visibles: Int,
    onCargarMas: () -> Unit,
    onAgregar: (Juego) -> Unit
) {
    val filtrados = remember(categoria, orden) {
        val porCategoria = DatosDemo.juegos.filter { categoria == "Todos" || it.categoria == categoria }
        when (orden) {
            1 -> porCategoria.sortedBy { it.precio }
            2 -> porCategoria.sortedByDescending { it.precio }
            else -> porCategoria
        }
    }
    val mostrados = filtrados.take(visibles)

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 156.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Destacados(
                ampliado = ampliado,
                onReservar = { onAgregar(DatosDemo.juegos.first()) },
                onCategoria = onCategoria
            )
        }
        item(span = { GridItemSpan(maxLineSpan) }) {
            Filtros(categoria, onCategoria, orden, onOrden)
        }
        items(mostrados, key = { it.id }) { juego ->
            TarjetaJuego(juego, onAgregar)
        }
        if (mostrados.size < filtrados.size) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(Modifier.fillMaxWidth().padding(top = 4.dp), contentAlignment = Alignment.Center) {
                    BotonContorno("Cargar Más Juegos", onCargarMas)
                }
            }
        }
    }
}

// --- Destacado + promociones ----------------------------------------------------------------

@Composable
private fun Destacados(ampliado: Boolean, onReservar: () -> Unit, onCategoria: (String) -> Unit) {
    if (ampliado) {
        Row(
            Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Lanzamiento(Modifier.weight(2f).fillMaxHeight(), onReservar)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PromoRpg(Modifier.weight(1f).fillMaxWidth()) { onCategoria("RPG") }
                PromoIndies(Modifier.weight(1f).fillMaxWidth()) { onCategoria("Aventura") }
            }
        }
    } else {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Lanzamiento(Modifier.fillMaxWidth(), onReservar)
            PromoRpg(Modifier.fillMaxWidth()) { onCategoria("RPG") }
            PromoIndies(Modifier.fillMaxWidth()) { onCategoria("Aventura") }
        }
    }
}

@Composable
private fun Lanzamiento(modifier: Modifier, onReservar: () -> Unit) {
    val forma = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .clip(forma)
            .background(
                Brush.linearGradient(listOf(Color(0xFF0B1B2B), Color(0xFF2A1458), Color(0xFF3A1546)))
            )
            .border(1.dp, BordeTarjeta, forma)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        EtiquetaNeon("GRAN LANZAMIENTO", Cian, Cian.copy(alpha = 0.15f))
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Cyber Drift 2077",
            color = TextoClaro,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Experimenta el futuro de las carreras callejeras. Gráficos next-gen y velocidad sin límites.",
            color = TextoClaro.copy(alpha = 0.75f),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(16.dp))
        BotonCompacto(
            texto = "RESERVAR AHORA",
            onClick = onReservar,
            icono = IconosMarlon.FlechaAdelante,
            iconoAlFinal = true
        )
    }
}

@Composable
private fun PromoRpg(modifier: Modifier, onClick: () -> Unit) {
    val forma = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .clip(forma)
            .background(Brush.linearGradient(listOf(Color(0xFF3A1546), Color(0xFF1B1464))))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "TOP VENTAS",
            color = Lila,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Juegos RPG",
            color = TextoClaro,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Ver Colección ›",
            color = TextoClaro,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
        )
    }
}

@Composable
private fun PromoIndies(modifier: Modifier, onClick: () -> Unit) {
    val forma = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .clip(forma)
            .background(FondoTarjeta)
            .border(1.dp, BordeTarjeta, forma)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Indies Destacados",
            color = Cian,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Descubre joyas ocultas y nuevas aventuras.",
            color = TextoSecundario,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

// --- Filtros --------------------------------------------------------------------------------

@Composable
private fun Filtros(
    categoria: String,
    onCategoria: (String) -> Unit,
    orden: Int,
    onOrden: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DatosDemo.categorias.forEach { nombre ->
                ChipCategoria(nombre, nombre == categoria) { onCategoria(nombre) }
            }
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            SelectorOrden(orden, onOrden)
        }
    }
}

@Composable
private fun ChipCategoria(texto: String, activo: Boolean, onClick: () -> Unit) {
    val forma = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .clip(forma)
            .background(if (activo) Cian.copy(alpha = 0.12f) else FondoTarjeta)
            .border(1.dp, if (activo) Cian else BordeTarjeta, forma)
            .clickable(role = Role.Tab, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = texto,
            color = if (activo) Cian else TextoClaro,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
        )
    }
}

@Composable
private fun SelectorOrden(orden: Int, onOrden: (Int) -> Unit) {
    var abierto by remember { mutableStateOf(false) }
    val forma = RoundedCornerShape(10.dp)
    Box {
        Row(
            modifier = Modifier
                .clip(forma)
                .background(FondoTarjeta)
                .border(1.dp, BordeTarjeta, forma)
                .clickable(role = Role.DropdownList) { abierto = true }
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = OpcionesOrden[orden],
                color = TextoClaro,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp)
            )
            Spacer(Modifier.width(8.dp))
            Icon(IconosMarlon.FlechaAbajo, null, tint = TextoClaro, modifier = Modifier.size(18.dp))
        }
        DropdownMenu(expanded = abierto, onDismissRequest = { abierto = false }) {
            OpcionesOrden.forEachIndexed { indice, opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        abierto = false
                        onOrden(indice)
                    }
                )
            }
        }
    }
}

// --- Tarjeta de juego -----------------------------------------------------------------------

@Composable
private fun TarjetaJuego(juego: Juego, onAgregar: (Juego) -> Unit) {
    val forma = RoundedCornerShape(14.dp)
    Column(
        Modifier
            .clip(forma)
            .background(FondoTarjeta)
            .border(1.dp, BordeTarjeta, forma)
    ) {
        Box {
            PortadaJuego(
                juego = juego,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.1f)
            )
            if (juego.etiqueta != null) {
                val esDescuento = juego.etiqueta.startsWith("-")
                EtiquetaNeon(
                    texto = juego.etiqueta,
                    colorTexto = if (esDescuento) RojoAlerta else Cian,
                    colorFondo = if (esDescuento) RojoAlertaFondo else FondoCampo,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Column(Modifier.padding(12.dp)) {
            Text(
                text = juego.categoria.uppercase(),
                color = TextoSecundario,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = juego.titulo,
                color = TextoClaro,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    if (juego.precioAnterior != null) {
                        Text(
                            text = juego.precioAnterior.comoPesos(),
                            color = TextoSecundario,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                    Text(
                        text = juego.precio.comoPesos(),
                        color = Cian,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Box(
                    Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(FondoCampo)
                        .clickable(role = Role.Button) { onAgregar(juego) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        IconosMarlon.Carrito,
                        contentDescription = "Añadir ${juego.nombre} al carrito",
                        tint = Cian,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}