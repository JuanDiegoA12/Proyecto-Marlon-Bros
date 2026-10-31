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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.componentes.BotonDegradado
import com.example.marlonbros.ui.componentes.CampoNeon
import com.example.marlonbros.ui.componentes.EncabezadoMarca
import com.example.marlonbros.ui.componentes.FondoCuadricula
import com.example.marlonbros.ui.componentes.SelectorNeon
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import com.example.marlonbros.ui.theme.TextoBotonOscuro

/** Departamentos de ejemplo; se reemplazarán por los reales cuando haya backend. */
val DepartamentosEjemplo = listOf("Ventas", "Caja", "Bodega", "Administración")


@Composable
fun RegistroEmpleadoScreen(
    onRegistrar: () -> Unit = {},
    onIniciarSesion: () -> Unit = {}
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var codigo by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var contrasena by rememberSaveable { mutableStateOf("") }
    var departamento by rememberSaveable { mutableStateOf<String?>(null) }

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
                    .border(1.dp, BordeTarjeta, formaTarjeta)
                    // Línea cian en el borde superior, como en la referencia.
                    .drawWithContent {
                        drawContent()
                        drawRect(Cian, size = Size(size.width, 2.dp.toPx()))
                    }
                    .padding(horizontal = 20.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EncabezadoMarca(
                    tamanoLogo = 100.dp,
                    separacion = 20.dp,
                    subtitulo = "Portal de Registro Interno",
                    estiloSubtitulo = MaterialTheme.typography.bodyLarge,
                    brilloLogo = true,
                    logoCircular = true
                )
                Spacer(Modifier.height(32.dp))

                CampoNeon(
                    etiqueta = "Nombre Completo",
                    valor = nombre,
                    onValorChange = { nombre = it },
                    marcador = "Ej. Juan Pérez",
                    iconoEtiqueta = IconosMarlon.Persona,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "ID / Código de Empleado",
                    valor = codigo,
                    onValorChange = { codigo = it },
                    marcador = "MB-XXXXX",
                    iconoEtiqueta = IconosMarlon.Gafete,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "Correo Corporativo",
                    valor = correo,
                    onValorChange = { correo = it },
                    marcador = "empleado@marlonbros.com",
                    iconoEtiqueta = IconosMarlon.Correo,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(Modifier.height(20.dp))
                CampoNeon(
                    etiqueta = "Contraseña",
                    valor = contrasena,
                    onValorChange = { contrasena = it },
                    marcador = "••••••••",
                    iconoEtiqueta = IconosMarlon.Candado,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(Modifier.height(20.dp))
                SelectorNeon(
                    etiqueta = "Departamento",
                    opciones = DepartamentosEjemplo,
                    seleccion = departamento,
                    onSeleccion = { departamento = it },
                    marcador = "Seleccione un departamento",
                    iconoEtiqueta = IconosMarlon.Edificio
                )
                Spacer(Modifier.height(36.dp))
                BotonDegradado(
                    texto = "Registrar Empleado",
                    onClick = onRegistrar,
                    icono = IconosMarlon.Registrar,
                    colorTexto = TextoBotonOscuro
                )
                Spacer(Modifier.height(24.dp))
                TextButton(onClick = onIniciarSesion) {
                    Text(
                        text = "¿Ya tienes cuenta? Iniciar Sesión",
                        color = Cian,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegistroEmpleadoScreenPreview() {
    MarlonBrosTheme { RegistroEmpleadoScreen() }
}
