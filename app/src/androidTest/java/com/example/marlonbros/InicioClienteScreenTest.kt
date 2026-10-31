package com.example.marlonbros

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marlonbros.navegacion.AppNavegacion
import com.example.marlonbros.ui.pantallas.InicioClienteScreen
import com.example.marlonbros.ui.theme.MarlonBrosTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InicioClienteScreenTest {

    @get:Rule
    val reglaCompose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraSubtituloClienteEnLugarDelDeEmpleado() {
        reglaCompose.setContent { MarlonBrosTheme { InicioClienteScreen() } }

        reglaCompose.onNodeWithText("MARLON BROS").assertIsDisplayed()
        reglaCompose.onNodeWithText("CLIENTE").assertIsDisplayed()
        reglaCompose.onNodeWithText("PRECISION IN MOTION").assertDoesNotExist()
        reglaCompose.onNodeWithText("Iniciar Sesión").assertIsDisplayed().assertIsSelected()
        reglaCompose.onNodeWithText("CORREO ELECTRÓNICO").assertIsDisplayed()
        reglaCompose.onNodeWithText("CONTRASEÑA").assertIsDisplayed()
        reglaCompose.onNodeWithText("CONECTAR").assertIsDisplayed()
    }

    @Test
    fun conectarLlevaAlPanelClienteTemporalYAtrasRegresa() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Cliente").performClick()
        reglaCompose.onNodeWithText("CONECTAR").performClick()
        reglaCompose.onNodeWithText("Panel Cliente", substring = true).assertIsDisplayed()

        Espresso.pressBack()
        reglaCompose.onNodeWithText("CLIENTE").assertIsDisplayed()
    }

    @Test
    fun registrarseLlevaARegistroCliente() {
        reglaCompose.setContent { MarlonBrosTheme { AppNavegacion() } }

        reglaCompose.onNodeWithText("Soy Cliente").performClick()
        reglaCompose.onNodeWithText("Registrarse").performClick()
        reglaCompose.onNodeWithText("CREAR CUENTA").performScrollTo().assertIsDisplayed()
    }
}
