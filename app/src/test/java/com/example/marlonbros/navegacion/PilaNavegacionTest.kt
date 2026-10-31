package com.example.marlonbros.navegacion

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PilaNavegacionTest {

    @Test
    fun empiezaEnInicioSinPoderRegresar() {
        val pila = PilaNavegacion()
        assertEquals(Pantalla.INICIO, pila.actual)
        assertFalse(pila.puedeRegresar)
    }

    @Test
    fun irAgregaPantalla() {
        val pila = PilaNavegacion()
        pila.ir(Pantalla.REGISTRO_CLIENTE)
        assertEquals(Pantalla.REGISTRO_CLIENTE, pila.actual)
        assertTrue(pila.puedeRegresar)
        assertEquals(listOf(Pantalla.INICIO, Pantalla.REGISTRO_CLIENTE), pila.historial)
    }

    @Test
    fun irALaMismaPantallaNoLaDuplica() {
        val pila = PilaNavegacion()
        pila.ir(Pantalla.INICIO_EMPLEADO)
        pila.ir(Pantalla.INICIO_EMPLEADO)
        assertEquals(2, pila.historial.size)
    }

    @Test
    fun regresarQuitaPantallaActual() {
        val pila = PilaNavegacion()
        pila.ir(Pantalla.INICIO_EMPLEADO)
        assertTrue(pila.regresar())
        assertEquals(Pantalla.INICIO, pila.actual)
    }

    @Test
    fun regresarEnLaRaizDevuelveFalse() {
        val pila = PilaNavegacion()
        assertFalse(pila.regresar())
        assertEquals(Pantalla.INICIO, pila.actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun pilaVaciaNoSePermite() {
        PilaNavegacion(emptyList())
    }
}
