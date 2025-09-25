package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun PanelControlScreen(
    onConfigAlarmas: () -> Unit,
    onCrearPlanta: () -> Unit,
    onConfigSensores: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Configuración", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onConfigAlarmas,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) { Text("Configuración de alarmas") }
        Button(
            onClick = onCrearPlanta,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) { Text("Creación de planta") }
        Button(
            onClick = onConfigSensores,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) { Text("Configuración de sensores") }
    }
}
