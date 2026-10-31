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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.ui.componentes.BotonDegradado
import com.example.marlonbros.ui.componentes.CampoNeon
import com.example.marlonbros.ui.componentes.ColoresCampo
import com.example.marlonbros.ui.componentes.EncabezadoMarca
import com.example.marlonbros.ui.componentes.FondoCuadricula
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.AroGris
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.FondoVioleta
import com.example.marlonbros.ui.theme.IconoCampoClaro
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoBotonOscuro
import com.example.marlonbros.ui.theme.TextoClaro

/**
 * Registro de cliente (solo diseño, sin validación ni backend).
 * [onCrearCuenta] e [onIniciarSesion] los decide la navegación.
 */
@Composable
fun RegistroClienteScreen(
    onCrearCuenta: () -> Unit = {},
    onIniciarSesion: () -> Unit = {}
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var confirmar by rememberSaveable { mutableStateOf("") }
    var mostrarContrasena by rememberSaveable { mutableStateOf(false) }

    FondoCuadricula {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val formaTarjeta = RoundedCornerShape(14.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(formaTarjeta)
                    .background(FondoTarjeta)
                    // Resplandor violeta en la parte baja de la tarjeta, como en la referencia.
                    .background(
                        Brush.verticalGradient(
                            0.6f to Color.Transparent,
                            1f to FondoVioleta.copy(alpha = 0.55f)
                        )
                    )
                    .border(1.dp, BordeTarjeta, formaTarjeta)
                    .padding(horizontal = 22.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EncabezadoMarca(
                    tamanoLogo = 84.dp,
                    separacion = 18.dp,
                    subtitulo = "Crea tu cuenta para acceder a hardware premium",
                    estiloSubtitulo = MaterialTheme.typography.bodyLarge,
                    brilloLogo = true,
                    logoCircular = true,
                    colorAro = AroGris
                )
                Spacer(Modifier.height(32.dp))

                CampoNeon(
                    etiqueta = "NOMBRE COMPLETO",
                    valor = nombre,
                    onValorChange = { nombre = it },
                    marcador = "Ingresa tu nombre",
                    icono = IconosMarlon.Persona,
                    colores = ColoresCampo.Claro,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "CORREO ELECTRÓNICO",
                    valor = correo,
                    onValorChange = { correo = it },
                    marcador = "ejemplo@correo.com",
                    icono = IconosMarlon.Correo,
                    colores = ColoresCampo.Claro,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "CONTRASEÑA",
                    valor = contrasena,
                    onValorChange = { contrasena = it },
                    marcador = "••••••••",
                    icono = IconosMarlon.Candado,
                    colores = ColoresCampo.Claro,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = if (mostrarContrasena) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    accionFinal = {
                        IconButton(onClick = { mostrarContrasena = !mostrarContrasena }) {
                            Icon(
                                imageVector = if (mostrarContrasena) IconosMarlon.Ojo else IconosMarlon.OjoTachado,
                                contentDescription = if (mostrarContrasena) "Ocultar contraseña" else "Mostrar contraseña",
                                tint = IconoCampoClaro,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "CONFIRMAR CONTRASEÑA",
                    valor = confirmar,
                    onValorChange = { confirmar = it },
                    marcador = "••••••••",
                    icono = IconosMarlon.CandadoReloj,
                    colores = ColoresCampo.Claro,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(Modifier.height(32.dp))
                BotonDegradado(
                    texto = "CREAR CUENTA",
                    onClick = onCrearCuenta,
                    colorTexto = TextoBotonOscuro,
                    pesoTexto = FontWeight.Medium,
                    espaciadoLetras = 2.sp
                )
                Spacer(Modifier.height(20.dp))
                TextButton(onClick = onIniciarSesion) {
                    Text(
                        text = buildAnnotatedString {
                            append("¿Ya tienes una cuenta? ")
                            withStyle(SpanStyle(color = Cian, fontWeight = FontWeight.Bold)) {
                                append("Inicia Sesión")
                            }
                        },
                        color = TextoClaro,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegistroClienteScreenPreview() {
    MarlonBrosTheme { RegistroClienteScreen() }
}
