package com.example.marlonbros.navegacion

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.marlonbros.ui.pantallas.InicioEmpleadoScreen
import com.example.marlonbros.ui.pantallas.InicioScreen
import com.example.marlonbros.ui.pantallas.PantallaTemporal

@Composable
fun AppNavegacion() {
    val pila = rememberSaveable(saver = PilaNavegacion.Saver) { PilaNavegacion() }

    BackHandler(enabled = pila.puedeRegresar) { pila.regresar() }

    when (pila.actual) {
        Pantalla.INICIO -> InicioScreen(
            onEmpleado = { pila.ir(Pantalla.INICIO_EMPLEADO) },
            onCliente = { pila.ir(Pantalla.REGISTRO_CLIENTE) }
        )
        Pantalla.INICIO_EMPLEADO -> InicioEmpleadoScreen(
            // Sin autenticación por ahora: Conectar lleva directo al panel temporal.
            onConectar = { pila.ir(Pantalla.PANEL_EMPLEADO) },
            onRegistrarse = { pila.ir(Pantalla.REGISTRO_EMPLEADO) }
        )
        Pantalla.REGISTRO_EMPLEADO -> PantallaTemporal("Registro Empleado")
        Pantalla.PANEL_EMPLEADO -> PantallaTemporal("Panel Empleado")
        Pantalla.REGISTRO_CLIENTE -> PantallaTemporal("Registro Cliente")
    }
}
