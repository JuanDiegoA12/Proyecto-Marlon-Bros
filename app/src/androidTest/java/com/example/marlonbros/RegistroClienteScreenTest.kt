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
import com.example.marlonbros.ui.pantallas.RegistroClienteScreen
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistroClienteScreenTest {

    @get:Rule
    val reglaCompose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraElementosDeLaReferencia() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroClienteScreen() } }

        reglaCompose.onNodeWithContentDescription("Logo Marlon Bros").assertIsDisplayed()
        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
        listOf(
            "Crea tu cuenta para acceder a hardware premium",
            "NOMBRE COMPLETO",
            "Ingresa tu nombre",
            "CORREO ELECTRÓNICO",
            "ejemplo@correo.com",
            "CONTRASEÑA",
            "CONFIRMAR CONTRASEÑA",
            "CREAR CUENTA"
        ).forEach { texto ->
            reglaCompose.onNodeWithText(texto).performScrollTo().assertIsDisplayed()
        }
        reglaCompose.onNodeWithText("Inicia Sesión", substring = true)
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun ojoMuestraYOcultaLaContrasena() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroClienteScreen() } }

        val campos = reglaCompose.onAllNodes(hasSetTextAction())
        campos[2].performScrollTo().performTextInput("secreto")
        reglaCompose.onNodeWithText("secreto").assertDoesNotExist()

        reglaCompose.onNodeWithContentDescription("Mostrar contraseña").performClick()
        reglaCompose.onNodeWithText("secreto").assertIsDisplayed()

        reglaCompose.onNodeWithContentDescription("Ocultar contraseña").performClick()
        reglaCompose.onNodeWithText("secreto").assertDoesNotExist()
    }

    @Test
    fun confirmarContrasenaQuedaOculta() {
        reglaCompose.setContent { MarlonBrosTheme { RegistroClienteScreen() } }

        val campos = reglaCompose.onAllNodes(hasSetTextAction())
        campos[3].performScrollTo().performTextInput("otraClave")
        reglaCompose.onNodeWithText("otraClave").assertDoesNotExist()
    }

    @Test
    fun botonesLlamanSusAcciones() {
        var crearCuenta = 0
        var iniciarSesion = 0
        reglaCompose.setContent {
            MarlonBrosTheme {
                RegistroClienteScreen(
                    onCrearCuenta = { crearCuenta++ },
                    onIniciarSesion = { iniciarSesion++ }
                )
            }
        }

        reglaCompose.onNodeWithText("CREAR CUENTA").performScrollTo().performClick()
        reglaCompose.onNodeWithText("Inicia Sesión", substring = true).performScrollTo().performClick()

        assertEquals(1, crearCuenta)
        assertEquals(1, iniciarSesion)
    }

    @Test
    fun crearCuentaVuelveAInicioClienteYAtrasVaAInicio() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Cliente").performClick()
        reglaCompose.onNodeWithText("Registrarse").performClick()
        reglaCompose.onNodeWithText("CREAR CUENTA").performScrollTo().performClick()

        reglaCompose.onNodeWithText("CLIENTE").assertIsDisplayed()
        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()

        // El registro no queda en el historial: atrás lleva directo a Inicio.
        Espresso.pressBack()
        reglaCompose.onNodeWithText("Soy Cliente").assertIsDisplayed()
    }
}
