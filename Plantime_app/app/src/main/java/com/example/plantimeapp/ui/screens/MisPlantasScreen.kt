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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.plantimeapp.ui.theme.GreenDark
import com.example.plantimeapp.ui.theme.GreenPrimary

@Composable
fun MisPlantasScreen(
    onGoCalendario: () -> Unit,
    onGoEstadisticas: () -> Unit,
    onGoCrearPlanta: () -> Unit,
) {
    // Datos de ejemplo para replicar la web
    data class Planta(val nombre: String, val sensor: String, val icono: String)
    val plantas = remember { listOf(
        Planta("Aloe Vera", "Patio", "🌿"),
        Planta("Hierbabuena", "Cocina", "🌱")
    ) }

    // Estado de toggles por planta
    data class Toggles(var temperatura: Boolean = false, var humedad: Boolean = false, var luz: Boolean = false)
    val toggles: MutableMap<String, Toggles> = remember {
        mutableStateMapOf<String, Toggles>().apply {
            plantas.forEach { this[it.nombre] = Toggles() }
        }
    }

    // Notas por planta
    val notas: MutableMap<String, MutableList<String>> = remember {
        mutableStateMapOf<String, MutableList<String>>().apply {
            plantas.forEach { this[it.nombre] = mutableStateListOf<String>() }
        }
    }
    var showNotasPlanta by remember { mutableStateOf<String?>(null) }
    var nuevaNota by remember { mutableStateOf("") }

    // Alarmas por planta
    data class Alarma(val fecha: String, val hora: String, val descripcion: String)
    val alarmas: MutableMap<String, MutableList<Alarma>> = remember {
        mutableStateMapOf<String, MutableList<Alarma>>().apply {
            plantas.forEach { this[it.nombre] = mutableStateListOf<Alarma>() }
        }
    }
    var showAlarmaPlanta by remember { mutableStateOf<String?>(null) }
    var alarmaFecha by remember { mutableStateOf("") }
    var alarmaHora by remember { mutableStateOf("") }
    var alarmaDescripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra de acciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onGoCalendario, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) {
                Text("Calendario")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = onGoEstadisticas, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) {
                Text("Estadísticas")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = onGoCrearPlanta, colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)) {
                Text("Crear Planta")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Grid/Lista de plantas
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(plantas) { planta ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(planta.icono, style = MaterialTheme.typography.headlineSmall)
                            Spacer(Modifier.width(8.dp))
                            Column(Modifier.weight(1f)) {
                                Text(planta.nombre, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                                Text("Sensor: ${planta.sensor}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        // Botones / toggles
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { showAlarmaPlanta = planta.nombre }, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) { Text("Alarma") }
                            Button(onClick = { showNotasPlanta = planta.nombre }, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) { Text("Checklist") }
                            Button(onClick = { /* Configuración futura */ }, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) { Text("Configuración") }

                            // Toggles estilo chips
                            val tg = toggles[planta.nombre]!!
                            ToggleChip(label = "Temperatura", checked = tg.temperatura) { tg.temperatura = it }
                            ToggleChip(label = "Humedad", checked = tg.humedad) { tg.humedad = it }
                            ToggleChip(label = "Luz", checked = tg.luz) { tg.luz = it }
                        }
                    }
                }
            }
        }
    }

    // Modal Notas
    if (showNotasPlanta != null) {
        val planta = showNotasPlanta!!
        AlertDialog(
            onDismissRequest = { showNotasPlanta = null },
            confirmButton = {
                TextButton(onClick = {
                    if (nuevaNota.isNotBlank()) {
                        notas[planta]?.add(nuevaNota.trim())
                        nuevaNota = ""
                    }
                }) { Text("Agregar") }
            },
            dismissButton = { TextButton(onClick = { showNotasPlanta = null; nuevaNota = "" }) { Text("Cerrar") } },
            title = { Text("Notas de $planta") },
            text = {
                Column {
                    notas[planta]?.forEachIndexed { idx, n ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(n)
                            TextButton(onClick = { notas[planta]?.removeAt(idx) }) { Text("Eliminar", color = MaterialTheme.colorScheme.error) }
                        }
                    }
                    OutlinedTextField(value = nuevaNota, onValueChange = { nuevaNota = it }, placeholder = { Text("Agregar nota...") }, modifier = Modifier.fillMaxWidth())
                }
            }
        )
    }

    // Modal Alarma
    if (showAlarmaPlanta != null) {
        val planta = showAlarmaPlanta!!
        AlertDialog(
            onDismissRequest = { showAlarmaPlanta = null },
            confirmButton = {
                TextButton(onClick = {
                    if (alarmaFecha.isNotBlank() && alarmaHora.isNotBlank() && alarmaDescripcion.isNotBlank()) {
                        alarmas[planta]?.add(Alarma(alarmaFecha, alarmaHora, alarmaDescripcion))
                        showAlarmaPlanta = null
                        alarmaFecha = ""; alarmaHora = ""; alarmaDescripcion = ""
                    }
                }) { Text("Guardar") }
            },
            dismissButton = { TextButton(onClick = { showAlarmaPlanta = null; alarmaFecha = ""; alarmaHora = ""; alarmaDescripcion = "" }) { Text("Cerrar") } },
            title = { Text("Asignar alarma a $planta") },
            text = {
                Column {
                    val list = alarmas[planta]
                    if (!list.isNullOrEmpty()) {
                        Column(Modifier.padding(bottom = 8.dp)) {
                            list.forEachIndexed { idx, a ->
                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("${'$'}{a.fecha} ${'$'}{a.hora} - ${'$'}{a.descripcion}")
                                    TextButton(onClick = { list.removeAt(idx) }) { Text("Eliminar", color = MaterialTheme.colorScheme.error) }
                                }
                            }
                        }
                    }
                    OutlinedTextField(value = alarmaFecha, onValueChange = { alarmaFecha = it }, label = { Text("Fecha (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = alarmaHora, onValueChange = { alarmaHora = it }, label = { Text("Hora (HH:mm)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = alarmaDescripcion, onValueChange = { alarmaDescripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
                }
            }
        )
    }
}

@Composable
private fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable RowScope.() -> Unit
) {
    // Sencilla emulación de FlowRow usando múltiples filas automáticas
    Column(modifier = modifier) {
        Row(horizontalArrangement = horizontalArrangement, modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}

@Composable
private fun ToggleChip(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = checked, onCheckedChange = onCheckedChange)
        Spacer(Modifier.width(4.dp))
        Text(label)
    }
}
