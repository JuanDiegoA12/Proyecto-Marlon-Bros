package com.example.marlonbros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.marlonbros.navegacion.AppNavegacion
import com.example.marlonbros.ui.theme.MarlonBrosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarlonBrosTheme {
                AppNavegacion()
            }
        }
    }
}
