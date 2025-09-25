package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistorialScreen() {
    val registros = remember {
        listOf(
            "Se creó planta: Aloe Vera",
            "Se configuró sensor: Patio",
            "Se agregó evento de calendario: Riego Aloe",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Historial", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(12.dp))
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                registros.forEach { Text(it) }
            }
        }
    }
}
