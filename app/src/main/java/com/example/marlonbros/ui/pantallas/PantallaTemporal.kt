package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.componentes.FondoCuadricula
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoClaro

/** Marcador de lugar para pantallas que aún no están diseñadas. */
@Composable
fun PantallaTemporal(titulo: String) {
    FondoCuadricula {
        Text(
            text = "$titulo\n(próximamente)",
            color = TextoClaro,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PantallaTemporalPreview() {
    MarlonBrosTheme { PantallaTemporal("Registro Cliente") }
}
