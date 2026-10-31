package com.example.marlonbros.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.componentes.BotonDegradado
import com.example.marlonbros.ui.componentes.CampoNeon
import com.example.marlonbros.ui.componentes.EncabezadoMarca
import com.example.marlonbros.ui.componentes.FondoCuadricula
import com.example.marlonbros.ui.componentes.PestanasNeon
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.MarlonBrosTheme

/**
 * Inicio de sesión de empleado (solo diseño, sin autenticación).
 * [onConectar] y [onRegistrarse] los decide la navegación.
 */
@Composable
fun InicioEmpleadoScreen(
    onConectar: () -> Unit = {},
    onRegistrarse: () -> Unit = {},
    onOlvideContrasena: () -> Unit = {}
) {
    var correo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }

    FondoCuadricula {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EncabezadoMarca(
                tamanoLogo = 130.dp,
                separacion = 32.dp,
                subtitulo = "PRECISION IN MOTION",
                brilloLogo = true
            )
            Spacer(Modifier.height(36.dp))

            val formaTarjeta = RoundedCornerShape(12.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(formaTarjeta)
                    .background(FondoTarjeta)
                    .border(1.dp, BordeTarjeta, formaTarjeta)
            ) {
                PestanasNeon(
                    pestanas = listOf("Iniciar Sesión", "Registrarse"),
                    seleccionada = 0,
                    onSeleccionar = { if (it == 1) onRegistrarse() }
                )
                Column(Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                    CampoNeon(
                        etiqueta = "CORREO ELECTRÓNICO",
                        valor = correo,
                        onValorChange = { correo = it },
                        marcador = "jugador@neonlink.com",
                        icono = IconosMarlon.Correo,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(Modifier.height(22.dp))
                    CampoNeon(
                        etiqueta = "CONTRASEÑA",
                        valor = contrasena,
                        onValorChange = { contrasena = it },
                        marcador = "••••••••",
                        icono = IconosMarlon.Candado,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    TextButton(
                        onClick = onOlvideContrasena,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = "¿Olvidaste tu contraseña?",
                            color = Cian,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    BotonDegradado(texto = "CONECTAR", onClick = onConectar)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun InicioEmpleadoScreenPreview() {
    MarlonBrosTheme { InicioEmpleadoScreen() }
}
