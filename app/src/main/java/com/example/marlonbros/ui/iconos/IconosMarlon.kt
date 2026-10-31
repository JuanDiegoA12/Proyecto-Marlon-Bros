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

    // Los trazos de Material están diseñados para la regla NonZero.
    private fun icono(nombre: String, trazo: String): ImageVector =
        ImageVector.Builder(
            name = nombre,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes(trazo),
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(Color.Black)
        ).build()
}
