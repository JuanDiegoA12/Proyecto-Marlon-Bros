package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marlonbros.R
import com.example.marlonbros.ui.componentes.BotonNeon
import com.example.marlonbros.ui.componentes.FondoCuadricula
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.Lila
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.VerdeAzulado
import com.example.marlonbros.ui.theme.Violeta

@Composable
fun InicioScreen(
    onEmpleado: () -> Unit = {},
    onCliente: () -> Unit = {}
) {
    FondoCuadricula {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Marlon Bros",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Spacer(Modifier.height(48.dp))
            Text(
                text = "MARLON BROS",
                color = Cian,
                style = MaterialTheme.typography.displaySmall.copy(
                    shadow = Shadow(color = Cian, blurRadius = 24f)
                )
            )
            Spacer(Modifier.height(48.dp))
            BotonNeon(
                texto = "Soy Empleado",
                icono = IconosMarlon.Gafete,
                colorBorde = VerdeAzulado,
                colorTexto = TextoClaro,
                colorIcono = Cian,
                onClick = onEmpleado
            )
            Spacer(Modifier.height(24.dp))
            BotonNeon(
                texto = "Soy Cliente",
                icono = IconosMarlon.Persona,
                colorBorde = Violeta,
                colorTexto = Lila,
                colorIcono = Lila,
                onClick = onCliente
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun InicioScreenPreview() {
    MarlonBrosTheme { InicioScreen() }
}
