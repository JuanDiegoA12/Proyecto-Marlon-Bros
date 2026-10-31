package com.example.marlonbros.navegacion

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver

/**
 * Pila de pantallas. La última es la que se muestra; la primera (raíz) nunca se quita.
 * Usa estado de Compose para que la UI se recomponga al navegar.
 */
class PilaNavegacion(inicial: List<Pantalla> = listOf(Pantalla.INICIO)) {

    init {
        require(inicial.isNotEmpty()) { "La pila necesita al menos una pantalla" }
    }

    private val pantallas = mutableStateListOf<Pantalla>().apply { addAll(inicial) }

    val actual: Pantalla get() = pantallas.last()

    val puedeRegresar: Boolean get() = pantallas.size > 1

    val historial: List<Pantalla> get() = pantallas.toList()

    fun ir(pantalla: Pantalla) {
        if (pantalla != actual) pantallas.add(pantalla)
    }

    /** Quita la pantalla actual. Devuelve false si ya está en la raíz. */
    fun regresar(): Boolean {
        if (!puedeRegresar) return false
        pantallas.removeAt(pantallas.lastIndex)
        return true
    }

    companion object {
        /** Guarda la pila como nombres del enum para sobrevivir a rotaciones. */
        val Saver: Saver<PilaNavegacion, Any> = listSaver(
            save = { pila -> pila.historial.map { it.name } },
            restore = { nombres -> PilaNavegacion(nombres.map(Pantalla::valueOf)) }
        )
    }
}
