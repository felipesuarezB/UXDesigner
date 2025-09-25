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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantimeapp.ui.state.AppState
import com.example.plantimeapp.ui.theme.GreenDark
import com.example.plantimeapp.ui.theme.GreenPrimary

@Composable
fun PanelControlScreen(onGoCalendario: () -> Unit) {
    var modoViaje by remember { mutableStateOf(false) }
    var modoTranquilidad by remember { mutableStateOf(false) }

    var compartirUsuario by remember { mutableStateOf("") }

    var showSensorModal by remember { mutableStateOf(false) }
    var nuevoSensorNombre by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Panel de control", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(12.dp))

        // Grid simple con columnas responsivas simuladas con columna + cards
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Modo viaje", style = MaterialTheme.typography.titleLarge)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(if (modoViaje) "ON" else "OFF")
                        Switch(checked = modoViaje, onCheckedChange = { modoViaje = it })
                    }
                }
            }

            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Modo tranquilidad", style = MaterialTheme.typography.titleLarge)
                    Text("permite bloquear las notificaciones", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(if (modoTranquilidad) "ON" else "OFF")
                        Switch(checked = modoTranquilidad, onCheckedChange = { modoTranquilidad = it })
                    }
                }
            }

            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Compartir cuentas", style = MaterialTheme.typography.titleLarge)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(value = compartirUsuario, onValueChange = { compartirUsuario = it }, placeholder = { Text("Usuario") }, modifier = Modifier.weight(1f))
                        Button(onClick = { if (compartirUsuario.isNotBlank()) compartirUsuario = "" }, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) {
                            Text("Compartir")
                        }
                    }
                }
            }

            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text("Sensores", style = MaterialTheme.typography.titleLarge)
                        Button(onClick = { showSensorModal = true }, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) { Text("Agregar sensor") }
                    }
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(AppState.sensores()) { s -> Text(s) }
                    }
                }
            }

            Card {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Calendario", style = MaterialTheme.typography.titleLarge)
                    Button(onClick = onGoCalendario, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) { Text("Ir al calendario") }
                }
            }
        }
    }

    if (showSensorModal) {
        AlertDialog(
            onDismissRequest = { showSensorModal = false },
            title = { Text("Agregar sensor") },
            text = {
                OutlinedTextField(value = nuevoSensorNombre, onValueChange = { nuevoSensorNombre = it }, placeholder = { Text("Nombre del sensor") }, modifier = Modifier.fillMaxWidth())
            },
            confirmButton = {
                TextButton(onClick = {
                    val nombre = nuevoSensorNombre.trim()
                    if (nombre.isNotEmpty()) {
                        AppState.agregarSensor(nombre)
                        showSensorModal = false
                        nuevoSensorNombre = ""
                    }
                }) { Text("Agregar") }
            },
            dismissButton = { TextButton(onClick = { showSensorModal = false; nuevoSensorNombre = "" }) { Text("Cancelar") } }
        )
    }
}
