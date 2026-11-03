package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.datos.CarritoEstado
import com.example.marlonbros.datos.LineaCarrito
import com.example.marlonbros.datos.comoPesos
import com.example.marlonbros.ui.componentes.BotonCompacto
import com.example.marlonbros.ui.componentes.BotonDegradado
import com.example.marlonbros.ui.componentes.PortadaJuego
import com.example.marlonbros.ui.componentes.TarjetaPanel
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario
import com.example.marlonbros.ui.theme.VerdeExito

/**
 * Carrito del cliente (mockup "Your Cart" / "Order Summary"), en español.
 * [onIrCatalogo] se usa desde el estado vacío y tras confirmar el pedido simulado.
 */
@Composable
fun CarritoScreen(
    carrito: CarritoEstado,
    ampliado: Boolean,
    onIrCatalogo: () -> Unit
) {
    var dialogoPago by rememberSaveable { mutableStateOf(false) }
    val lineas = carrito.lineas

    if (lineas.isEmpty()) {
        CarritoVacio(onIrCatalogo)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tu Carrito",
                color = Cian,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            if (ampliado) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ListaLineas(Modifier.weight(2f), lineas, carrito)
                    ResumenPedido(Modifier.weight(1f), carrito) { dialogoPago = true }
                }
            } else {
                ListaLineas(Modifier.fillMaxWidth(), lineas, carrito)
                ResumenPedido(Modifier.fillMaxWidth(), carrito) { dialogoPago = true }
            }
        }
    }

    if (dialogoPago) {
        AlertDialog(
            onDismissRequest = { dialogoPago = false },
            containerColor = FondoTarjeta,
            title = { Text("Pedido registrado (simulado)", color = TextoClaro) },
            text = {
                Text(
                    "El pago por PSE y el registro de la venta se conectarán con el backend " +
                            "en el siguiente avance.",
                    color = TextoSecundario
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogoPago = false
                    carrito.vaciar()
                    onIrCatalogo()
                }) { Text("Entendido", color = Cian) }
            }
        )
    }
}

@Composable
private fun CarritoVacio(onIrCatalogo: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(IconosMarlon.Carrito, null, tint = Cian, modifier = Modifier.size(56.dp))
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Tu carrito está vacío",
            color = TextoClaro,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        BotonCompacto("VER CATÁLOGO", onIrCatalogo)
    }
}

@Composable
private fun ListaLineas(modifier: Modifier, lineas: List<LineaCarrito>, carrito: CarritoEstado) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        lineas.forEach { LineaItem(it, carrito) }
    }
}

@Composable
private fun LineaItem(linea: LineaCarrito, carrito: CarritoEstado) {
    val juego = linea.juego
    TarjetaPanel {
        Row {
            PortadaJuego(
                juego = juego,
                modifier = Modifier.size(width = 88.dp, height = 112.dp).clip(RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f).height(112.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.Top) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = juego.nombre,
                            color = TextoClaro,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "${juego.edicion} · ${juego.plataforma}",
                            color = TextoSecundario,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    IconButton(onClick = { carrito.quitar(juego.id) }, modifier = Modifier.size(32.dp)) {
                        Icon(
                            IconosMarlon.Basura,
                            contentDescription = "Quitar ${juego.nombre}",
                            tint = TextoSecundario,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SelectorCantidad(linea.cantidad, juego.nombre) { delta ->
                        carrito.cambiarCantidad(juego.id, delta)
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = linea.subtotal.comoPesos(),
                        color = Cian,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectorCantidad(cantidad: Int, nombre: String, onCambio: (Int) -> Unit) {
    val forma = RoundedCornerShape(8.dp)
    Row(
        modifier = Modifier
            .clip(forma)
            .border(1.dp, BordeTarjeta, forma),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotonCantidad(IconosMarlon.Menos, "Quitar una unidad de $nombre") { onCambio(-1) }
        Text(
            text = cantidad.toString(),
            color = TextoClaro,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(28.dp)
        )
        BotonCantidad(IconosMarlon.Mas, "Agregar una unidad de $nombre") { onCambio(+1) }
    }
}

@Composable
private fun BotonCantidad(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    onClick: () -> Unit
) {
    Box(
        Modifier.size(36.dp).clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, contentDescription = descripcion, tint = TextoClaro, modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun ResumenPedido(modifier: Modifier, carrito: CarritoEstado, onPagar: () -> Unit) {
    val unidades = carrito.unidades
    TarjetaPanel(modifier, relleno = 20.dp) {
        Text(
            text = "Resumen del Pedido",
            color = TextoClaro,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(color = BordeTarjeta)
        Spacer(Modifier.height(12.dp))
        FilaResumen(
            "Subtotal ($unidades ${if (unidades == 1) "artículo" else "artículos"})",
            carrito.subtotal.comoPesos(),
            TextoClaro
        )
        Spacer(Modifier.height(10.dp))
        FilaResumen("Entrega digital", "Gratis", VerdeExito)
        Spacer(Modifier.height(10.dp))
        FilaResumen("Impuestos", "Se calculan al pagar", TextoSecundario)
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(color = BordeTarjeta)
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Total",
                color = TextoClaro,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = carrito.subtotal.comoPesos(),
                color = Cian,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
        Spacer(Modifier.height(20.dp))
        BotonDegradado(texto = "Proceder al pago", onClick = onPagar, icono = IconosMarlon.FlechaAdelante)
    }
}

@Composable
private fun FilaResumen(etiqueta: String, valor: String, colorValor: androidx.compose.ui.graphics.Color) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = etiqueta,
            color = TextoSecundario,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valor,
            color = colorValor,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}