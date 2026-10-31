package com.example.marlonbros

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marlonbros.navegacion.AppNavegacion
import com.example.marlonbros.ui.pantallas.RegistroEmpleadoScreen
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroEmpleadoScreenTest {

    @get:Rule
    val reglaCompose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraElementosDeLaReferencia() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroEmpleadoScreen() } }

        reglaCompose.onNodeWithContentDescription("Logo Marlon Bros").assertIsDisplayed()
        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
        reglaCompose.onNodeWithText("Portal de Registro Interno").assertIsDisplayed()
        listOf(
            "Nombre Completo",
            "ID / Código de Empleado",
            "Correo Corporativo",
            "Contraseña",
            "Departamento",
            "Seleccione un departamento",
            "Registrar Empleado",
            "¿Ya tienes cuenta? Iniciar Sesión"
        ).forEach { texto ->
            reglaCompose.onNodeWithText(texto).performScrollTo().assertIsDisplayed()
        }
    }

    @Test
    fun camposAceptanTextoYContrasenaQuedaOculta() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroEmpleadoScreen() } }

        val campos = reglaCompose.onAllNodes(hasSetTextAction())
        campos[0].performTextInput("Juan Pérez")
        campos[3].performScrollTo().performTextInput("secreto")

        reglaCompose.onNodeWithText("Juan Pérez").assertIsDisplayed()
        reglaCompose.onNodeWithText("secreto").assertDoesNotExist()
    }

    @Test
    fun selectorDeDepartamentoCambiaLaSeleccion() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroEmpleadoScreen() } }

        reglaCompose.onNodeWithText("Seleccione un departamento").performScrollTo().performClick()
        reglaCompose.onNodeWithText("Bodega").performClick()

        reglaCompose.onNodeWithText("Bodega").assertIsDisplayed()
        reglaCompose.onNodeWithText("Seleccione un departamento").assertDoesNotExist()
    }

    @Test
    fun botonesLlamanSusAcciones() {
        var registrar = 0
        var iniciarSesion = 0
        reglaCompose.setContent {
            MarlonBrosTheme {
                RegistroEmpleadoScreen(
                    onRegistrar = { registrar++ },
                    onIniciarSesion = { iniciarSesion++ }
                )
            }
        }

        reglaCompose.onNodeWithText("Registrar Empleado").performScrollTo().performClick()
        reglaCompose.onNodeWithText("¿Ya tienes cuenta? Iniciar Sesión").performScrollTo().performClick()

        assertEquals(1, registrar)
        assertEquals(1, iniciarSesion)
    }

    @Test
    fun registrarVuelveAInicioEmpleadoYAtrasVaAInicio() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Empleado").performClick()
        reglaCompose.onNodeWithText("Registrarse").performClick()
        reglaCompose.onNodeWithText("Registrar Empleado").performScrollTo().performClick()

        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()

        // El registro no queda en el historial: atrás lleva directo a Inicio.
        Espresso.pressBack()
        reglaCompose.onNodeWithText("Soy Empleado").assertIsDisplayed()
    }
}
