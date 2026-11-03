package com.example.marlonbros.ui.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marlonbros.datos.Juego
import com.example.marlonbros.ui.iconos.IconosMarlon
import com.example.marlonbros.ui.theme.BordeTarjeta
import com.example.marlonbros.ui.theme.Cian
import com.example.marlonbros.ui.theme.FondoCampo
import com.example.marlonbros.ui.theme.FondoTarjeta
import com.example.marlonbros.ui.theme.TextoBotonDegradado
import com.example.marlonbros.ui.theme.TextoClaro
import com.example.marlonbros.ui.theme.TextoSecundario
import com.example.marlonbros.ui.theme.VioletaIntenso

/** Tarjeta oscura con borde, base de las tarjetas de los paneles. */
@Composable
fun TarjetaPanel(
    modifier: Modifier = Modifier,
    relleno: Dp = 16.dp,
    contenido: @Composable ColumnScope.() -> Unit
) {
    val forma = RoundedCornerShape(14.dp)
    Column(
        modifier = modifier
            .clip(forma)
            .background(FondoTarjeta)
            .border(1.dp, BordeTarjeta, forma)
            .padding(relleno),
        content = contenido
    )
}

/** Botón pequeño con degradado cian → violeta ("Añadir Juego", "Reservar ahora"). */
@Composable
fun BotonCompacto(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icono: ImageVector? = null,
    iconoAlFinal: Boolean = false,
    colorTexto: Color = TextoBotonDegradado
) {
    val forma = RoundedCornerShape(10.dp)
    Row(
        modifier = modifier
            .height(44.dp)
            .clip(forma)
            .background(Brush.horizontalGradient(listOf(Cian, VioletaIntenso)))
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (icono != null && !iconoAlFinal) {
            Icon(icono, contentDescription = null, tint = colorTexto, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = texto,
            color = colorTexto,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        if (icono != null && iconoAlFinal) {
            Spacer(Modifier.width(8.dp))
            Icon(icono, contentDescription = null, tint = colorTexto, modifier = Modifier.size(20.dp))
        }
    }
}

/** Botón con contorno tenue ("Cargar Más Juegos"). */
@Composable
fun BotonContorno(texto: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val forma = RoundedCornerShape(24.dp)
    Box(
        modifier = modifier
            .clip(forma)
            .border(BorderStroke(1.dp, BordeTarjeta), forma)
            .background(FondoTarjeta)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 28.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = TextoClaro,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 13.sp)
        )
    }
}

/** Campo de búsqueda con lupa y marcador. */
@Composable
fun CampoBusqueda(
    valor: String,
    onValorChange: (String) -> Unit,
    marcador: String,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(10.dp)
    BasicTextField(
        value = valor,
        onValueChange = onValorChange,
        singleLine = true,
        textStyle = TextStyle(color = TextoClaro, fontSize = 15.sp),
        cursorBrush = SolidColor(Cian),
        modifier = modifier.fillMaxWidth(),
        decorationBox = { campo ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(forma)
                    .background(FondoCampo)
                    .border(1.dp, BordeTarjeta, forma)
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    IconosMarlon.Buscar,
                    contentDescription = null,
                    tint = TextoClaro,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.width(10.dp))
                Box(Modifier.weight(1f)) {
                    if (valor.isEmpty()) {
                        Text(marcador, color = TextoSecundario, fontSize = 15.sp, maxLines = 1)
                    }
                    campo()
                }
            }
        }
    )
}

/** Etiqueta pequeña tipo "chip" (estado de pedido, "NUEVO", "-15%"...). */
@Composable
fun EtiquetaNeon(
    texto: String,
    colorTexto: Color,
    colorFondo: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(colorFondo)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = texto,
            color = colorTexto,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp)
        )
    }
}

/** Marcador para secciones cuyo diseño aún no está en los mockups (Inventario, Perfil, etc.). */
@Composable
fun SeccionPendiente(titulo: String, icono: ImageVector, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icono, contentDescription = null, tint = Cian, modifier = Modifier.size(56.dp))
        Spacer(Modifier.height(16.dp))
        Text(
            text = titulo,
            color = TextoClaro,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Disponible en el siguiente avance",
            color = TextoSecundario,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

private val PaletasPortada = listOf(
    Color(0xFF1B1464) to Color(0xFFB04BE0),
    Color(0xFF062A3B) to Color(0xFF00B8D4),
    Color(0xFF3A1546) to Color(0xFFFF4D8D),
    Color(0xFF10202E) to Color(0xFF3F7CAC),
    Color(0xFF2A1458) to Color(0xFF1DE9B6)
)

/**
 * Portada de relleno de un juego (degradado + control). Se reemplazará por la imagen
 * servida desde Cloudinary cuando exista el backend.
 */
@Composable
fun PortadaJuego(juego: Juego, modifier: Modifier = Modifier) {
    val (inicio, fin) = PaletasPortada[juego.id % PaletasPortada.size]
    Box(
        modifier = modifier.background(Brush.linearGradient(listOf(inicio, fin))),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            IconosMarlon.Gamepad,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.35f),
            modifier = Modifier.fillMaxSize(0.45f)
        )
        Text(
            text = juego.plataforma.uppercase(),
            color = Color.White.copy(alpha = 0.85f),
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp),
            modifier = Modifier.align(Alignment.BottomStart).padding(8.dp)
        )
    }
}