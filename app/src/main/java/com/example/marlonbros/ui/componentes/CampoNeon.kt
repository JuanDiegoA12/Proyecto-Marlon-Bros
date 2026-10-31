package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.FondoCampoClaro
import com.example.marlonbros.ui.theme.IconoCampoClaro
import com.example.marlonbros.ui.theme.TextoCampoClaro
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario

internal val FormaCampo = RoundedCornerShape(6.dp)

/** Colores del interior de un campo. */
@Immutable
data class ColoresCampo(
    val fondo: Color,
    val texto: Color,
    val marcador: Color,
    val icono: Color,
    val bordeEnfocado: Color
) {
    companion object {
        /** Campo oscuro (Inicio de sesión y Registro Empleado). */
        val Oscuro = ColoresCampo(
            fondo = FondoCampo,
            texto = TextoClaro,
            marcador = TextoSecundario,
            icono = TextoClaro.copy(alpha = 0.8f),
            bordeEnfocado = Cian.copy(alpha = 0.6f)
        )

        /** Campo claro (Registro Cliente). */
        val Claro = ColoresCampo(
            fondo = FondoCampoClaro,
            texto = TextoCampoClaro,
            marcador = TextoSecundario,
            icono = IconoCampoClaro,
            bordeEnfocado = Cian
        )
    }
}

/** Etiqueta monospace de un campo, con ícono pequeño opcional a la izquierda. */
@Composable
fun EtiquetaCampo(texto: String, icono: ImageVector? = null) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (icono != null) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = TextoClaro.copy(alpha = 0.85f),
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = texto,
            color = TextoClaro.copy(alpha = 0.85f),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

/**
 * Campo con etiqueta monospace arriba.
 * [icono] se dibuja dentro del campo; [iconoEtiqueta] junto a la etiqueta;
 * [accionFinal] va pegado a la derecha (por ejemplo, el ojo para mostrar la contraseña).
 */
@Composable
fun CampoNeon(
    etiqueta: String,
    valor: String,
    onValorChange: (String) -> Unit,
    marcador: String,
    modifier: Modifier = Modifier,
    icono: ImageVector? = null,
    iconoEtiqueta: ImageVector? = null,
    colores: ColoresCampo = ColoresCampo.Oscuro,
    altura: Dp = 52.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    accionFinal: (@Composable () -> Unit)? = null
) {
    var enfocado by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        EtiquetaCampo(etiqueta, iconoEtiqueta)
        Spacer(Modifier.height(10.dp))
        BasicTextField(
            value = valor,
            onValueChange = onValorChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colores.texto),
            cursorBrush = SolidColor(Cian),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { enfocado = it.isFocused },
            decorationBox = { campo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(altura)
                        .background(colores.fondo, FormaCampo)
                        .border(
                            1.dp,
                            if (enfocado) colores.bordeEnfocado else Color.Transparent,
                            FormaCampo
                        )
                        .padding(start = 14.dp, end = if (accionFinal != null) 4.dp else 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icono != null) {
                        Icon(
                            imageVector = icono,
                            contentDescription = null,
                            tint = colores.icono,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                    }
                    Box(Modifier.weight(1f)) {
                        if (valor.isEmpty()) {
                            Text(
                                text = marcador,
                                color = colores.marcador,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        campo()
                    }
                    accionFinal?.invoke()
                }
            }
        )
    }
}
