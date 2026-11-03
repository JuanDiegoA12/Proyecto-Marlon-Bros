package com.example.marlonbros.datos

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver

class CarritoEstado(inicial: List<Pair<Int, Int>> = emptyList()) {

    private val items = mutableStateListOf<Pair<Int, Int>>().apply { addAll(inicial) }

    val lineas: List<LineaCarrito>
        get() = items.mapNotNull { (id, cantidad) ->
            DatosDemo.juegoPorId(id)?.let { LineaCarrito(it, cantidad) }
        }

    val unidades: Int get() = items.sumOf { it.second }

    val subtotal: Int get() = lineas.sumOf { it.subtotal }

    fun agregar(juego: Juego) {
        val i = items.indexOfFirst { it.first == juego.id }
        if (i >= 0) items[i] = items[i].first to items[i].second + 1
        else items.add(juego.id to 1)
    }

    fun cambiarCantidad(idJuego: Int, delta: Int) {
        val i = items.indexOfFirst { it.first == idJuego }
        if (i < 0) return
        val nueva = items[i].second + delta
        if (nueva <= 0) items.removeAt(i) else items[i] = idJuego to nueva
    }

    fun quitar(idJuego: Int) {
        items.removeAll { it.first == idJuego }
    }

    fun vaciar() = items.clear()

    companion object {
        val Saver: Saver<CarritoEstado, Any> = listSaver<CarritoEstado, Int>(
            save = { carrito -> carrito.items.flatMap { listOf(it.first, it.second) } },
            restore = { plano -> CarritoEstado(plano.chunked(2).map { it[0] to it[1] }) }
        )
    }
}