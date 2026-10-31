package com.example.marlonbros

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marlonbros.navegacion.AppNavegacion
import com.example.marlonbros.ui.pantallas.InicioScreen
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InicioScreenTest {

    @get:Rule
    val reglaCompose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraLogoTituloYBotones() {
        reglaCompose.setContent { MarlonBrosTheme { InicioScreen() } }

        reglaCompose.onNodeWithContentDescription("Logo Marlon Bros").assertIsDisplayed()
        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
        reglaCompose.onNodeWithText("Soy Empleado").assertIsDisplayed().assertHasClickAction()
        reglaCompose.onNodeWithText("Soy Cliente").assertIsDisplayed().assertHasClickAction()
    }

    @Test
    fun botonesLlamanSusAcciones() {
        var empleado = 0
        var cliente = 0
        reglaCompose.setContent {
            MarlonBrosTheme {
                InicioScreen(onEmpleado = { empleado++ }, onCliente = { cliente++ })
            }
        }

        reglaCompose.onNodeWithText("Soy Empleado").performClick()
        reglaCompose.onNodeWithText("Soy Cliente").performClick()

        assertEquals(1, empleado)
        assertEquals(1, cliente)
    }

    @Test
    fun clienteNavegaYAtrasRegresaAInicio() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Cliente").performClick()
        reglaCompose.onNodeWithText("CLIENTE").assertIsDisplayed()

        Espresso.pressBack()
        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
    }

    @Test
    fun empleadoNavegaAPantallaTemporal() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Empleado").performClick()
        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()
    }
}
