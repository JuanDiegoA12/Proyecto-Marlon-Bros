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

    private fun icono(nombre: String, trazo: String): ImageVector =
        ImageVector.Builder(
            name = nombre,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes(trazo),
            pathFillType = PathFillType.EvenOdd,
            fill = SolidColor(Color.Black)
        ).build()
}
