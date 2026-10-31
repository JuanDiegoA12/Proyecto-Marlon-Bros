package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.shadow.Shadow as SombraForma
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.marlonbros.R
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.TextoClaro

/**
 * Logo + "MARLON BROS" con brillo cian y un subtítulo opcional.
 * [brilloLogo] agrega resplandor cian; [logoCircular] recorta el logo en círculo con aro cian.
 */
@Composable
fun EncabezadoMarca(
    modifier: Modifier = Modifier,
    tamanoLogo: Dp = 200.dp,
    separacion: Dp = 48.dp,
    subtitulo: String? = null,
    estiloSubtitulo: TextStyle = MaterialTheme.typography.labelLarge,
    brilloLogo: Boolean = false,
    logoCircular: Boolean = false
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val forma = if (logoCircular) CircleShape else RoundedCornerShape(4.dp)
        var logoModifier: Modifier = Modifier
        if (brilloLogo) {
            logoModifier = logoModifier.dropShadow(
                forma,
                SombraForma(radius = 20.dp, color = Cian.copy(alpha = 0.35f))
            )
        }
        if (logoCircular) {
            logoModifier = logoModifier
                .clip(forma)
                .border(3.dp, Cian, forma)
        }
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Marlon Bros",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(tamanoLogo)
                .then(logoModifier)
        )
        Spacer(Modifier.height(separacion))
        Text(
            text = "MARLON BROS",
            color = Cian,
            style = MaterialTheme.typography.displaySmall.copy(
                shadow = Shadow(color = Cian, blurRadius = 24f)
            )
        )
        if (subtitulo != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = subtitulo,
                color = TextoClaro.copy(alpha = 0.85f),
                style = estiloSubtitulo
            )
        }
    }
}
