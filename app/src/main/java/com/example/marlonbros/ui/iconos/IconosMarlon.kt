package com.example.marlonbros.ui.iconos

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/** Íconos de Material (Apache 2.0) definidos en Kotlin para no depender de material-icons-extended. */
object IconosMarlon {

    /** Material "badge" (gafete). */
    val Gafete: ImageVector by lazy {
        icono(
            nombre = "Gafete",
            trazo = "M20,7h-5V4c0-1.1-0.9-2-2-2h-2C9.9,2 9,2.9 9,4v3H4C2.9,7 2,7.9 2,9v11" +
                "c0,1.1 0.9,2 2,2h16c1.1,0 2-0.9 2-2V9C22,7.9 21.1,7 20,7z" +
                "M9,12c0.83,0 1.5,0.67 1.5,1.5S9.83,15 9,15s-1.5-0.67-1.5-1.5S8.17,12 9,12z" +
                "M12,18H6v-0.75c0-1 2-1.5 3-1.5s3,0.5 3,1.5V18z" +
                "M13,9h-2V4h2V9z" +
                "M18,16.5h-4V15h4V16.5z" +
                "M18,13.5h-4V12h4V13.5z"
        )
    }

    /** Material "person" (persona). */
    val Persona: ImageVector by lazy {
        icono(
            nombre = "Persona",
            trazo = "M12,12c2.21,0 4-1.79 4-4s-1.79-4-4-4-4,1.79-4,4 1.79,4 4,4z" +
                "M12,14c-2.67,0-8,1.34-8,4v2h16v-2c0-2.66-5.33-4-8-4z"
        )
    }

    /** Material "mail_outline" (correo). */
    val Correo: ImageVector by lazy {
        icono(
            nombre = "Correo",
            trazo = "M20,4H4c-1.1,0-1.99,0.9-1.99,2L2,18c0,1.1 0.9,2 2,2h16c1.1,0 2-0.9 2-2V6" +
                "c0-1.1-0.9-2-2-2z" +
                "M20,18H4V8l8,5 8-5v10z" +
                "M12,11L4,6h16l-8,5z"
        )
    }

    /** Material "lock_outline" (candado). */
    val Candado: ImageVector by lazy {
        icono(
            nombre = "Candado",
            trazo = "M12,17c1.1,0 2-0.9 2-2s-0.9-2-2-2-2,0.9-2,2 0.9,2 2,2z" +
                "M18,8h-1V6c0-2.76-2.24-5-5-5S7,3.24 7,6v2H6c-1.1,0-2,0.9-2,2v10" +
                "c0,1.1 0.9,2 2,2h12c1.1,0 2-0.9 2-2V10c0-1.1-0.9-2-2-2z" +
                "M8.9,6c0-1.71 1.39-3.1 3.1-3.1s3.1,1.39 3.1,3.1v2H8.9V6z" +
                "M18,20H6V10h12v10z"
        )
    }

    /** Material "domain" (edificio / departamento). */
    val Edificio: ImageVector by lazy {
        icono(
            nombre = "Edificio",
            trazo = "M12,7V3H2v18h20V7H12z" +
                "M6,19H4v-2h2V19zM6,15H4v-2h2V15zM6,11H4V9h2V11zM6,7H4V5h2V7z" +
                "M10,19H8v-2h2V19zM10,15H8v-2h2V15zM10,11H8V9h2V11zM10,7H8V5h2V7z" +
                "M20,19h-8v-2h2v-2h-2v-2h2v-2h-2V9h8V19z" +
                "M18,11h-2v2h2V11zM18,15h-2v2h2V15z"
        )
    }

    /** Material "expand_more" (flecha desplegable). */
    val FlechaAbajo: ImageVector by lazy {
        icono(
            nombre = "FlechaAbajo",
            trazo = "M16.59,8.59L12,13.17 7.41,8.59 6,10l6,6 6-6z"
        )
    }

    /** Material "how_to_reg" (persona con palomita). */
    val Registrar: ImageVector by lazy {
        icono(
            nombre = "Registrar",
            trazo = "M9,17l3-2.94c-0.39-0.04-0.68-0.06-1-0.06-2.67,0-8,1.34-8,4v2h9l-3-3z" +
                "M11,12c2.21,0 4-1.79 4-4s-1.79-4-4-4-4,1.79-4,4 1.79,4 4,4z" +
                "M15.47,20.5L12,17l1.4-1.41 2.07,2.08 5.13-5.17 1.4,1.41z"
        )
    }

    /** Material "visibility" (ojo: mostrar contraseña). */
    val Ojo: ImageVector by lazy {
        icono(
            nombre = "Ojo",
            trazo = "M12,4.5C7,4.5 2.73,7.61 1,12c1.73,4.39 6,7.5 11,7.5s9.27-3.11 11-7.5" +
                "c-1.73-4.39-6-7.5-11-7.5z" +
                "M12,17c-2.76,0-5-2.24-5-5s2.24-5 5-5 5,2.24 5,5-2.24,5-5,5z" +
                "M12,9c-1.66,0-3,1.34-3,3s1.34,3 3,3 3-1.34 3-3-1.34-3-3-3z"
        )
    }

    /** Material "visibility_off" (ojo tachado: contraseña oculta). */
    val OjoTachado: ImageVector by lazy {
        icono(
            nombre = "OjoTachado",
            trazo = "M12,7c2.76,0 5,2.24 5,5 0,0.65-0.13,1.26-0.36,1.83l2.92,2.92" +
                "c1.51-1.26 2.7-2.89 3.43-4.75-1.73-4.39-6-7.5-11-7.5-1.4,0-2.74,0.25-3.98,0.7" +
                "l2.16,2.16C10.74,7.13 11.35,7 12,7z" +
                "M2,4.27l2.28,2.28 0.46,0.46C3.08,8.3 1.78,10.02 1,12c1.73,4.39 6,7.5 11,7.5" +
                "1.55,0 3.03-0.3 4.38-0.84l0.42,0.42L19.73,22 21,20.73 3.27,3 2,4.27z" +
                "M7.53,9.8l1.55,1.55c-0.05,0.21-0.08,0.43-0.08,0.65 0,1.66 1.34,3 3,3" +
                "0.22,0 0.44-0.03 0.65-0.08l1.55,1.55c-0.67,0.33-1.41,0.53-2.2,0.53" +
                "-2.76,0-5-2.24-5-5 0-0.79 0.2-1.53 0.53-2.2z" +
                "M11.84,9.02l3.15,3.15 0.02-0.16c0-1.66-1.34-3-3-3l-0.17,0.01z"
        )
    }

    /**
     * Candado pequeño arriba a la izquierda + reloj abajo a la derecha (confirmar contraseña).
     * Dibujo propio: las piezas no se traslapan, así EvenOdd recorta los huecos correctamente.
     */
    val CandadoReloj: ImageVector by lazy {
        icono(
            nombre = "CandadoReloj",
            trazo =
                // Arco del candado
                "M3.2,7V5a3.3,3.3 0 0 1 6.6,0V7h-1.7V5a1.6,1.6 0 0 0 -3.2,0V7z" +
                // Cuerpo del candado (contorno) y ojo de la cerradura
                "M1,7h11v8h-11z" +
                "M2.8,8.8h7.4v4.4h-7.4z" +
                "M5.8,10.2h1.4v1.6h-1.4z" +
                // Reloj: aro y manecillas
                "M12.5,17a5,5 0 1 0 10,0a5,5 0 1 0 -10,0z" +
                "M13.9,17a3.6,3.6 0 1 0 7.2,0a3.6,3.6 0 1 0 -7.2,0z" +
                "M16.9,14.6h1.2v3h-1.2z" +
                "M18.1,16.4h1.8v1.2h-1.8z",
            tipoRelleno = PathFillType.EvenOdd
        )
    }

    /** Material "menu". */
    val Menu: ImageVector by lazy {
        icono(nombre = "Menu", trazo = "M3,18h18v-2L3,16v2zM3,13h18v-2L3,11v2zM3,6v2h18L21,6L3,6z")
    }

    /** Material "notifications". */
    val Campana: ImageVector by lazy {
        icono(nombre = "Campana", trazo = "M12,22c1.1,0 2,-0.9 2,-2h-4c0,1.1 0.89,2 2,2zM18,16v-5c0,-3.07 -1.64,-5.64 -4.5,-6.32L13.5,4c0,-0.83 -0.67,-1.5 -1.5,-1.5s-1.5,0.67 -1.5,1.5v0.68C7.63,5.36 6,7.92 6,11v5l-2,2v1h16v-1l-2,-2z")
    }

    /** Material "shopping_cart". */
    val Carrito: ImageVector by lazy {
        icono(nombre = "Carrito", trazo = "M7,18c-1.1,0 -1.99,0.9 -1.99,2S5.9,22 7,22s2,-0.9 2,-2 -0.9,-2 -2,-2zM1,2v2h2l3.6,7.59 -1.35,2.45c-0.16,0.28 -0.25,0.61 -0.25,0.96 0,1.1 0.9,2 2,2h12v-2H7.42c-0.14,0 -0.25,-0.11 -0.25,-0.25l0.03,-0.12 0.9,-1.63h7.45c0.75,0 1.41,-0.41 1.75,-1.03l3.58,-6.49c0.08,-0.14 0.12,-0.31 0.12,-0.48 0,-0.55 -0.45,-1 -1,-1H5.21l-0.94,-2H1zM17,18c-1.1,0 -1.99,0.9 -1.99,2s0.89,2 1.99,2 2,-0.9 2,-2 -0.9,-2 -2,-2z")
    }

    /** Material "search". */
    val Buscar: ImageVector by lazy {
        icono(nombre = "Buscar", trazo = "M15.5,14h-0.79l-0.28,-0.27C15.41,12.59 16,11.11 16,9.5 16,5.91 13.09,3 9.5,3S3,5.91 3,9.5 5.91,16 9.5,16c1.61,0 3.09,-0.59 4.23,-1.57l0.27,0.28v0.79l5,4.99L20.49,19l-4.99,-5zM9.5,14C7.01,14 5,11.99 5,9.5S7.01,5 9.5,5 14,7.01 14,9.5 11.99,14 9.5,14z")
    }

    /** Material "delete". */
    val Basura: ImageVector by lazy {
        icono(nombre = "Basura", trazo = "M6,19c0,1.1 0.9,2 2,2h8c1.1,0 2,-0.9 2,-2V7H6v12zM19,4h-3.5l-1,-1h-5l-1,1H5v2h14V4z")
    }

    /** Material "add". */
    val Mas: ImageVector by lazy {
        icono(nombre = "Mas", trazo = "M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z")
    }

    /** Material "remove". */
    val Menos: ImageVector by lazy {
        icono(nombre = "Menos", trazo = "M19,13H5v-2h14v2z")
    }

    /** Material "add_circle_outline". */
    val MasCirculo: ImageVector by lazy {
        icono(nombre = "MasCirculo", trazo = "M13,7h-2v4H7v2h4v4h2v-4h4v-2h-4V7zM12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2zM12,20c-4.41,0 -8,-3.59 -8,-8s3.59,-8 8,-8 8,3.59 8,8 -3.59,8 -8,8z")
    }

    /** Material "check_circle_outline". */
    val CheckCirculo: ImageVector by lazy {
        icono(nombre = "CheckCirculo", trazo = "M16.59,7.58L10,14.17l-3.59,-3.58L5,12l5,5 8,-8zM12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2zM12,20c-4.42,0 -8,-3.58 -8,-8s3.58,-8 8,-8 8,3.58 8,8 -3.58,8 -8,8z")
    }

    /** Material "dashboard". */
    val Tablero: ImageVector by lazy {
        icono(nombre = "Tablero", trazo = "M3,13h8L11,3L3,3v10zM3,21h8v-6L3,15v6zM13,21h8L21,11h-8v10zM13,3v6h8L21,3h-8z")
    }

    /** Material "bar_chart". */
    val Grafica: ImageVector by lazy {
        icono(nombre = "Grafica", trazo = "M5,9.2h3L8,19H5zM10.6,5h2.8v14h-2.8zM16.2,13H19v6h-2.8z")
    }

    /** Material "inventory_2". */
    val Inventario: ImageVector by lazy {
        icono(nombre = "Inventario", trazo = "M20,2H4c-1,0 -2,0.9 -2,2v3.01c0,0.72 0.43,1.34 1,1.69V20c0,1.1 1.1,2 2,2h14c0.9,0 2,-0.9 2,-2V8.7c0.57,-0.35 1,-0.97 1,-1.69V4c0,-1.1 -1,-2 -2,-2zM15,14H9v-2h6v2zM20,7H4V4h16v3z")
    }

    /** Material "warning". */
    val Alerta: ImageVector by lazy {
        icono(nombre = "Alerta", trazo = "M1,21h22L12,2 1,21zM13,18h-2v-2h2v2zM13,14h-2v-4h2v4z")
    }

    /** Material "trending_up". */
    val Tendencia: ImageVector by lazy {
        icono(nombre = "Tendencia", trazo = "M16,6l2.29,2.29 -4.88,4.88 -4,-4L2,16.59 3.41,18l6,-6 4,4 6.3,-6.29L22,12V6z")
    }

    /** Material "person_add". */
    val PersonaMas: ImageVector by lazy {
        icono(nombre = "PersonaMas", trazo = "M15,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM6,10V7H4v3H1v2h3v3h2v-3h3v-2H6zM15,14c-2.67,0 -8,1.34 -8,4v2h16v-2c0,-2.66 -5.33,-4 -8,-4z")
    }

    /** Material "headset". */
    val Auricular: ImageVector by lazy {
        icono(nombre = "Auricular", trazo = "M12,1c-4.97,0 -9,4.03 -9,9v7c0,1.66 1.34,3 3,3h3v-8H5v-2c0,-3.87 3.13,-7 7,-7s7,3.13 7,7v2h-4v8h3c1.66,0 3,-1.34 3,-3v-7c0,-4.97 -4.03,-9 -9,-9z")
    }

    /** Material "qr_code_scanner". */
    val Escaner: ImageVector by lazy {
        icono(nombre = "Escaner", trazo = "M9.5,6.5v3h-3v-3H9.5M11,5H5v6h6V5L11,5zM9.5,14.5v3h-3v-3H9.5M11,13H5v6h6V13L11,13zM17.5,6.5v3h-3v-3H17.5M19,5h-6v6h6V5L19,5zM13,13h1.5v1.5H13V13zM14.5,14.5H16V16h-1.5V14.5zM16,13h1.5v1.5H16V13zM13,16h1.5v1.5H13V16zM14.5,17.5H16V19h-1.5V17.5zM16,16h1.5v1.5H16V16zM17.5,14.5H19V16h-1.5V14.5zM17.5,17.5H19V19h-1.5V17.5zM22,7h-2V4h-3V2h5V7zM22,22v-5h-2v3h-3v2H22zM2,22h5v-2H4v-3H2V22zM2,2v5h2V4h3V2H2z")
    }

    /** Material "sports_esports". */
    val Gamepad: ImageVector by lazy {
        icono(nombre = "Gamepad", trazo = "M21.58,16.09l-1.09,-7.66C20.21,6.46 18.52,5 16.53,5H7.47C5.48,5 3.79,6.46 3.51,8.43l-1.09,7.66C2.2,17.63 3.39,19 4.94,19h0c0.68,0 1.32,-0.27 1.8,-0.75L9,16h6l2.25,2.25c0.48,0.48 1.13,0.75 1.8,0.75h0C20.61,19 21.8,17.63 21.58,16.09zM11,11H9v2H8v-2H6v-1h2V8h1v2h2V11zM15,10c-0.55,0 -1,-0.45 -1,-1c0,-0.55 0.45,-1 1,-1s1,0.45 1,1C16,9.55 15.55,10 15,10zM17,13c-0.55,0 -1,-0.45 -1,-1c0,-0.55 0.45,-1 1,-1s1,0.45 1,1C18,12.55 17.55,13 17,13z")
    }

    /** Material "local_offer". */
    val Etiqueta: ImageVector by lazy {
        icono(nombre = "Etiqueta", trazo = "M21.41,11.58l-9,-9C12.05,2.22 11.55,2 11,2H4c-1.1,0 -2,0.9 -2,2v7c0,0.55 0.22,1.05 0.59,1.42l9,9c0.36,0.36 0.86,0.58 1.41,0.58 0.55,0 1.05,-0.22 1.41,-0.59l7,-7c0.37,-0.36 0.59,-0.86 0.59,-1.41 0,-0.55 -0.23,-1.06 -0.59,-1.42zM5.5,7C4.67,7 4,6.33 4,5.5S4.67,4 5.5,4 7,4.67 7,5.5 6.33,7 5.5,7z")
    }

    /** Material "arrow_forward". */
    val FlechaAdelante: ImageVector by lazy {
        icono(nombre = "FlechaAdelante", trazo = "M12,4l-1.41,1.41L16.17,11H4v2h12.17l-5.58,5.59L12,20l8,-8z")
    }

    /** Material "exit_to_app". */
    val Salir: ImageVector by lazy {
        icono(nombre = "Salir", trazo = "M10.09,15.59L11.5,17l5,-5 -5,-5 -1.41,1.41L12.67,11H3v2h9.67l-2.58,2.59zM19,3H5c-1.11,0 -2,0.9 -2,2v4h2V5h14v14H5v-4H3v4c0,1.1 0.89,2 2,2h14c1.1,0 2,-0.9 2,-2V5c0,-1.1 -0.9,-2 -2,-2z")
    }

    /** Material "list_alt". */
    val ListaAlt: ImageVector by lazy {
        icono(nombre = "ListaAlt", trazo = "M19,5v14H5V5H19M19,3H5C3.9,3 3,3.9 3,5v14c0,1.1 0.9,2 2,2h14c1.1,0 2,-0.9 2,-2V5C21,3.9 20.1,3 19,3L19,3zM14,17H7v-2h7V17zM17,13H7v-2h10V13zM17,9H7V7h10V9z")
    }

    /** Material "sync". */
    val Sincronizar: ImageVector by lazy {
        icono(nombre = "Sincronizar", trazo = "M12,4V1L8,5l4,4V6c3.31,0 6,2.69 6,6 0,1.01 -0.25,1.97 -0.7,2.8l1.46,1.46C19.54,15.03 20,13.57 20,12c0,-4.42 -3.58,-8 -8,-8zM12,18c-3.31,0 -6,-2.69 -6,-6 0,-1.01 0.25,-1.97 0.7,-2.8L5.24,7.74C4.46,8.97 4,10.43 4,12c0,4.42 3.58,8 8,8v3l4,-4 -4,-4v3z")
    }

    /** Material "attach_money". */
    val Dinero: ImageVector by lazy {
        icono(nombre = "Dinero", trazo = "M11.8,10.9c-2.27,-0.59 -3,-1.2 -3,-2.15 0,-1.09 1.01,-1.85 2.7,-1.85 1.78,0 2.44,0.85 2.5,2.1h2.21c-0.07,-1.72 -1.12,-3.3 -3.21,-3.81V3h-3v2.16c-1.94,0.42 -3.5,1.68 -3.5,3.61 0,2.31 1.91,3.46 4.7,4.13 2.5,0.6 3,1.48 3,2.41 0,0.69 -0.49,1.79 -2.7,1.79 -2.06,0 -2.87,-0.92 -2.98,-2.1h-2.2c0.12,2.19 1.76,3.42 3.68,3.83V21h3v-2.15c1.95,-0.37 3.5,-1.5 3.5,-3.55 0,-2.84 -2.43,-3.81 -4.7,-4.4z")
    }

    // Los trazos de Material están diseñados para la regla NonZero.
    private fun icono(
        nombre: String,
        trazo: String,
        tipoRelleno: PathFillType = PathFillType.NonZero
    ): ImageVector =
        ImageVector.Builder(
            name = nombre,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes(trazo),
            pathFillType = tipoRelleno,
            fill = SolidColor(Color.Black)
        ).build()
}