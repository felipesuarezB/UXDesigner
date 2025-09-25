package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EstadisticasScreen() {
    var sensor by remember { mutableStateOf("") }
    var rango by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Estadísticas", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(12.dp))

        Card(Modifier.padding(bottom = 12.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text("Elige un sensor", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = sensor,
                    onValueChange = { sensor = it },
                    placeholder = { Text("Patio / Cocina / Habitación") },
                    modifier = Modifier.fillMaxSize()
                )

                Spacer(Modifier.height(12.dp))
                Text("Elige un rango", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = rango,
                    onValueChange = { rango = it },
                    placeholder = { Text("Día / Semana / Mes") },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column {
            Card(Modifier.padding(bottom = 8.dp)) { Column(Modifier.padding(16.dp)) { Text("Gráfico de Temperatura (placeholder)") } }
            Card(Modifier.padding(bottom = 8.dp)) { Column(Modifier.padding(16.dp)) { Text("Gráfico de Humedad (placeholder)") } }
            Card { Column(Modifier.padding(16.dp)) { Text("Gráfico de Luz (placeholder)") } }
        }
    }
}
