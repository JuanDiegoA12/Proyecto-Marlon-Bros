package com.example.marlonbros.ui.pantallas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.marlonbros.datos.CarritoEstado
import com.example.marlonbros.datos.DatosDemo
import com.example.marlonbros.ui.componentes.DestinoNav
import com.example.marlonbros.ui.componentes.IconoConInsignia
import com.example.marlonbros.ui.componentes.ItemMenu
import com.example.marlonbros.ui.componentes.MarcoPanel
import com.example.marlonbros.ui.componentes.SeccionPendiente
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.MarlonBrosTheme

private const val TAB_CATALOGO = 0
private const val TAB_CARRITO = 2
private const val JUEGOS_POR_PAGINA = 4

/**
 * Panel del cliente. Pestañas: Catálogo, Ofertas, Carrito, Cuenta.
 * Catálogo y Carrito tienen diseño; Ofertas y Cuenta quedan para el siguiente avance.
 */
@Composable
fun PanelClienteScreen(onCerrarSesion: () -> Unit = {}) {
    // El estado se guarda aquí para conservarlo al cambiar de pestaña y al rotar.
    var tab by rememberSaveable { mutableIntStateOf(TAB_CATALOGO) }
    var categoria by rememberSaveable { mutableStateOf("Todos") }
    var orden by rememberSaveable { mutableIntStateOf(0) }
    var visibles by rememberSaveable { mutableIntStateOf(JUEGOS_POR_PAGINA) }
    val carrito = rememberSaveable(saver = CarritoEstado.Saver) {
        CarritoEstado(DatosDemo.carritoInicial)
    }

    val destinos = remember {
        listOf(
            DestinoNav("Catálogo", IconosMarlon.Gamepad),
            DestinoNav("Ofertas", IconosMarlon.Etiqueta),
            DestinoNav("Carrito", IconosMarlon.Carrito),
            DestinoNav("Cuenta", IconosMarlon.Persona)
        )
    }

    MarcoPanel(
        destinos = destinos,
        seleccionado = tab,
        onSeleccionar = { tab = it },
        itemsMenu = listOf(
            ItemMenu("Catálogo", IconosMarlon.Gamepad) { tab = TAB_CATALOGO },
            ItemMenu("Mi carrito", IconosMarlon.Carrito) { tab = TAB_CARRITO },
            ItemMenu("Cerrar sesión", IconosMarlon.Salir, onCerrarSesion)
        ),
        accionSuperior = {
            IconoConInsignia(IconosMarlon.Carrito, "Ver carrito", carrito.unidades) {
                tab = TAB_CARRITO
            }
        }
    ) { ampliado ->
        when (tab) {
            TAB_CATALOGO -> CatalogoContenido(
                ampliado = ampliado,
                categoria = categoria,
                onCategoria = {
                    categoria = it
                    visibles = JUEGOS_POR_PAGINA
                },
                orden = orden,
                onOrden = { orden = it },
                visibles = visibles,
                onCargarMas = { visibles += JUEGOS_POR_PAGINA },
                onAgregar = { carrito.agregar(it) }
            )
            TAB_CARRITO -> CarritoScreen(
                carrito = carrito,
                ampliado = ampliado,
                onIrCatalogo = { tab = TAB_CATALOGO }
            )
            else -> SeccionPendiente(destinos[tab].etiqueta, destinos[tab].icono)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PanelClientePreview() {
    MarlonBrosTheme { PanelClienteScreen() }
}

@Preview(widthDp = 1000, heightDp = 700)
@Composable
private fun PanelClienteAnchoPreview() {
    MarlonBrosTheme { PanelClienteScreen() }
}