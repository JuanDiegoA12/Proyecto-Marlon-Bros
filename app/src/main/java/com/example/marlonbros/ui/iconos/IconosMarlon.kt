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
