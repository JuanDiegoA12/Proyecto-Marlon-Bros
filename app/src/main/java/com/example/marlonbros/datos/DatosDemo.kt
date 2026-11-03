package com.example.marlonbros.datos

import java.util.Locale


data class Juego(
    val id: Int,
    val nombre: String,
    val plataforma: String,
    val categoria: String,
    val precio: Int,
    val precioAnterior: Int? = null,
    val etiqueta: String? = null,
    val edicion: String = "Edición Digital"
) {
    val titulo: String get() = "$nombre ($plataforma)"
}

enum class EstadoPedido(val etiqueta: String) {
    COMPLETADO("Completado"),
    EN_TRANSITO("En Tránsito"),
    PENDIENTE("Pendiente")
}

data class Pedido(
    val codigo: String,
    val producto: String,
    val valor: String,
    val estado: EstadoPedido,
    val esAccesorio: Boolean = false
)

data class Tarea(
    val id: Int,
    val titulo: String,
    val detalle: String,
    val esStock: Boolean = false
)

data class LineaCarrito(val juego: Juego, val cantidad: Int) {
    val subtotal: Int get() = juego.precio * cantidad
}

/** 240000 -> "$240.000" (formato colombiano). */
fun Int.comoPesos(): String =
    "\$" + String.format(Locale.US, "%,d", this).replace(',', '.')

object DatosDemo {

    val categorias = listOf("Todos", "Acción", "RPG", "Deportes", "Aventura")

    val juegos = listOf(
        Juego(1, "Cyber Drift 2077", "PS5", "Acción", 240_000, etiqueta = "NUEVO"),
        Juego(2, "Fantasy Realms", "Xbox Series X", "RPG", 280_000, edicion = "Edición Estándar"),
        Juego(3, "FC Pro 2024", "PS5", "Deportes", 297_500, precioAnterior = 350_000, etiqueta = "-15%"),
        Juego(4, "Lost Horizon", "PC", "Aventura", 150_000),
        Juego(5, "Shadow Blade", "PS5", "Acción", 210_000),
        Juego(6, "Dragon's Oath", "Xbox Series X", "RPG", 230_000),
        Juego(7, "Turbo Kart Rush", "Nintendo Switch", "Deportes", 189_900),
        Juego(8, "Mystic Isles", "PC", "Aventura", 127_500, precioAnterior = 150_000, etiqueta = "-15%")
    )

    fun juegoPorId(id: Int): Juego? = juegos.firstOrNull { it.id == id }

    /** Pares (idJuego, cantidad) con los que arranca el carrito, igual que el mockup (total $520.000). */
    val carritoInicial = listOf(1 to 1, 2 to 1)

    val tareas = listOf(
        Tarea(1, "Preparar pedido #4502", "Prioridad Alta · 3 Artículos"),
        Tarea(2, "Actualizar stock de Cyber Drift", "Recepción Zona A", esStock = true),
        Tarea(3, "Verificar control Xbox #118", "Prioridad Media · 1 Artículo"),
        Tarea(4, "Etiquetar consolas PS5", "Bodega B · 6 Unidades", esStock = true),
        Tarea(5, "Empacar pedido #4498", "Prioridad Baja · 2 Artículos")
    )

    val pedidosRecientes = listOf(
        Pedido("#ORD-9823", "Cyberpunk 2077 - PS5", "\$210.000", EstadoPedido.COMPLETADO),
        Pedido("#ORD-9822", "Astro A50 Gen 4", "\$1'250.000", EstadoPedido.EN_TRANSITO, esAccesorio = true),
        Pedido("#ORD-9821", "Elden Ring - Xbox Series X", "\$230.000", EstadoPedido.PENDIENTE)
    )

    val diasSemana = listOf("LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM")
    val ventasSemana = listOf(0.45f, 0.60f, 0.50f, 0.75f, 0.65f, 0.90f, 0.70f)
}