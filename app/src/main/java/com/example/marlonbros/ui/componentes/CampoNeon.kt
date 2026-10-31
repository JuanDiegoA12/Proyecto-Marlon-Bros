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
import androidx.compose.ui.unit.dp
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario

/** Campo con etiqueta monospace arriba, fondo oscuro e ícono a la izquierda. */
@Composable
fun CampoNeon(
    etiqueta: String,
    valor: String,
    onValorChange: (String) -> Unit,
    marcador: String,
    icono: ImageVector,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var enfocado by remember { mutableStateOf(false) }
    val forma = RoundedCornerShape(6.dp)

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = etiqueta,
            color = TextoClaro.copy(alpha = 0.85f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.height(10.dp))
        BasicTextField(
            value = valor,
            onValueChange = onValorChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = TextoClaro),
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
                        .height(52.dp)
                        .background(FondoCampo, forma)
                        .border(
                            1.dp,
                            if (enfocado) Cian.copy(alpha = 0.6f) else Color.Transparent,
                            forma
                        )
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icono,
                        contentDescription = null,
                        tint = TextoClaro.copy(alpha = 0.8f),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Box(Modifier.weight(1f)) {
                        if (valor.isEmpty()) {
                            Text(
                                text = marcador,
                                color = TextoSecundario,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        campo()
                    }
                }
            }
        )
    }
}
