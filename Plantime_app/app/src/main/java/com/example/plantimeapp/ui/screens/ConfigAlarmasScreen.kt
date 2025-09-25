package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigAlarmasScreen() {
    var hora by remember { mutableStateOf(LocalTime.now()) }
    var fecha by remember { mutableStateOf(LocalDate.now()) }
    var descripcion by remember { mutableStateOf("") }
    var alarmas by remember { mutableStateOf(listOf<Triple<LocalDate, LocalTime, String>>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Configuración de Alarmas", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        // Campos de fecha y hora (placeholders, ya que DatePicker/TimePicker requieren más integración)
        OutlinedTextField(
            value = fecha.toString(),
            onValueChange = { /* No editable directamente */ },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        OutlinedTextField(
            value = hora.toString(),
            onValueChange = { /* No editable directamente */ },
            label = { Text("Hora") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                if (descripcion.isNotBlank()) {
                    alarmas = alarmas + Triple(fecha, hora, descripcion)
                    descripcion = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Agregar alarma") }
        Spacer(Modifier.height(20.dp))
        Text("Alarmas agregadas", style = MaterialTheme.typography.titleMedium)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(alarmas) { alarma ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("${alarma.third}", style = MaterialTheme.typography.bodyLarge)
                        Text("Fecha: ${alarma.first}", style = MaterialTheme.typography.bodySmall)
                        Text("Hora: ${alarma.second}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
