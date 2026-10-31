package com.example.marlonbros.navegacion

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.marlonbros.ui.pantallas.InicioClienteScreen
import com.example.marlonbros.ui.pantallas.InicioEmpleadoScreen
import com.example.marlonbros.ui.pantallas.InicioScreen
import com.example.marlonbros.ui.pantallas.PantallaTemporal
import com.example.marlonbros.ui.pantallas.RegistroClienteScreen
import com.example.marlonbros.ui.pantallas.RegistroEmpleadoScreen

@Composable
fun AppNavegacion() {
    val pila = rememberSaveable(saver = PilaNavegacion.Saver) { PilaNavegacion() }

    BackHandler(enabled = pila.puedeRegresar) { pila.regresar() }

    when (pila.actual) {
        Pantalla.INICIO -> InicioScreen(
            onEmpleado = { pila.ir(Pantalla.INICIO_EMPLEADO) },
            onCliente = { pila.ir(Pantalla.INICIO_CLIENTE) }
        )
        Pantalla.INICIO_EMPLEADO -> InicioEmpleadoScreen(
            // Sin autenticación por ahora: Conectar lleva directo al panel temporal.
            onConectar = { pila.ir(Pantalla.PANEL_EMPLEADO) },
            onRegistrarse = { pila.ir(Pantalla.REGISTRO_EMPLEADO) }
        )
        Pantalla.REGISTRO_EMPLEADO -> RegistroEmpleadoScreen(
            // Sin backend por ahora: registrar simula éxito y vuelve al inicio de sesión.
            onRegistrar = { pila.volverA(Pantalla.INICIO_EMPLEADO) },
            onIniciarSesion = { pila.volverA(Pantalla.INICIO_EMPLEADO) }
        )
        Pantalla.PANEL_EMPLEADO -> PantallaTemporal("Panel Empleado")
        Pantalla.INICIO_CLIENTE -> InicioClienteScreen(
            // Sin autenticación por ahora: Conectar lleva directo al panel temporal.
            onConectar = { pila.ir(Pantalla.PANEL_CLIENTE) },
            onRegistrarse = { pila.ir(Pantalla.REGISTRO_CLIENTE) }
        )
        Pantalla.REGISTRO_CLIENTE -> RegistroClienteScreen(
            // Sin backend por ahora: crear cuenta simula éxito y vuelve al inicio de sesión.
            onCrearCuenta = { pila.volverA(Pantalla.INICIO_CLIENTE) },
            onIniciarSesion = { pila.volverA(Pantalla.INICIO_CLIENTE) }
        )
        Pantalla.PANEL_CLIENTE -> PantallaTemporal("Panel Cliente")
    }
}
