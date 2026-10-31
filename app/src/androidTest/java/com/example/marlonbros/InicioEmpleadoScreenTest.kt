package com.example.marlonbros

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marlonbros.navegacion.AppNavegacion
import com.example.marlonbros.ui.pantallas.InicioEmpleadoScreen
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InicioEmpleadoScreenTest {

    @get:Rule
    val reglaCompose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraElementosDeLaReferencia() {
        reglaCompose.setContent { MarlonBrosTheme { InicioEmpleadoScreen() } }

        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
        reglaCompose.onNodeWithText("PRECISION IN MOTION").assertIsDisplayed()
        reglaCompose.onNodeWithText("Iniciar Sesión").assertIsDisplayed().assertIsSelected()
        reglaCompose.onNodeWithText("Registrarse").assertIsDisplayed()
        reglaCompose.onNodeWithText("CORREO ELECTRÓNICO").assertIsDisplayed()
        reglaCompose.onNodeWithText("CONTRASEÑA").assertIsDisplayed()
        reglaCompose.onNodeWithText("¿Olvidaste tu contraseña?").assertIsDisplayed()
        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()
    }

    @Test
    fun camposAceptanTexto() {
        reglaCompose.setContent { MarlonBrosTheme { InicioEmpleadoScreen() } }

        val campos = reglaCompose.onAllNodes(hasSetTextAction())
        campos[0].performTextInput("ana@marlonbros.com")
        campos[0].assertTextContains("ana@marlonbros.com")
        campos[1].performTextInput("secreto")
        // La contraseña se muestra oculta, no en texto plano.
        reglaCompose.onNodeWithText("secreto").assertDoesNotExist()
    }

    @Test
    fun botonesLlamanSusAcciones() {
        var conectar = 0
        var registrarse = 0
        reglaCompose.setContent {
            MarlonBrosTheme {
                InicioEmpleadoScreen(
                    onConectar = { conectar++ },
                    onRegistrarse = { registrarse++ }
                )
            }
        }

        reglaCompose.onNodeWithText("CONECTAR").performClick()
        reglaCompose.onNodeWithText("Registrarse").performClick()

        assertEquals(1, conectar)
        assertEquals(1, registrarse)
    }

    @Test
    fun conectarLlevaAlPanelTemporalYAtrasRegresa() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Empleado").performClick()
        reglaCompose.onNodeWithText("CONECTAR").performClick()
        reglaCompose.onNodeWithText("Panel Empleado", substring = true).assertIsDisplayed()

        Espresso.pressBack()
        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()
    }

    @Test
    fun registrarseLlevaARegistroEmpleadoTemporal() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Empleado").performClick()
        reglaCompose.onNodeWithText("Registrarse").performClick()
        reglaCompose.onNodeWithText("Registro Empleado", substring = true).assertIsDisplayed()
    }
}
