package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantimeapp.ui.state.AppState

@Composable
fun CalendarioScreen() {
    var showEventoModal by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var inicio by remember { mutableStateOf("") } // YYYY-MM-DD
    var hora by remember { mutableStateOf("") }   // HH:mm
    var duracion by remember { mutableStateOf("") } // as string for input

    val meses = listOf(
        "enero", "febrero", "marzo", "abril", "mayo", "junio",
        "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    )
    var mesIndex by remember { mutableStateOf(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)) }
    var anioActual by remember { mutableStateOf(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)) }

    val mesActual = meses[mesIndex]
    val diasDelMes by remember(mesIndex, anioActual) {
        mutableStateOf(run {
            val cal = java.util.Calendar.getInstance()
            cal.set(java.util.Calendar.YEAR, anioActual)
            cal.set(java.util.Calendar.MONTH, mesIndex)
            val dias = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
            (1..dias).toList()
        })
    }

    fun cambiarMes(delta: Int) {
        var idx = mesIndex + delta
        var anio = anioActual
        if (idx < 0) { idx = 11; anio -= 1 }
        if (idx > 11) { idx = 0; anio += 1 }
        mesIndex = idx
        anioActual = anio
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Calendario", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { cambiarMes(-1) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("<") }
            Spacer(Modifier.width(12.dp))
            Text("${mesActual} ${anioActual}", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.width(12.dp))
            Button(onClick = { cambiarMes(1) }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text(">") }
        }

        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            // Calendario simple con dots
            Card(Modifier.weight(1f)) {
                Column(Modifier.padding(12.dp)) {
                    // Representación simple en grid 7xN
                    val days = diasDelMes
                    val rows = (days.size + 6) / 7
                    repeat(rows) { r ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            (0 until 7).forEach { c ->
                                val day = r * 7 + c + 1
                                if (day <= days.size) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                                        Text(day.toString())
                                        val hasEvent = AppState.eventos().any { e -> e.inicio.takeLast(2).toIntOrNull() == day }
                                        if (hasEvent) {
                                            Spacer(Modifier.height(2.dp))
                                            Box(Modifier.size(6.dp).align(Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                                                Surface(color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small) { Box(Modifier.size(6.dp)) }
                                            }
                                        } else {
                                            Spacer(Modifier.height(8.dp))
                                        }
                                    }
                                } else {
                                    Spacer(Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            // Lista de eventos y agregar
            Card(Modifier.weight(1f)) {
                Column(Modifier.padding(12.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Eventos programados", style = MaterialTheme.typography.titleLarge)
                        Button(onClick = { showEventoModal = true }) { Text("Agregar evento") }
                    }
                    Spacer(Modifier.height(8.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(AppState.eventos()) { e ->
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(e.nombre)
                                Text("${'$'}{e.inicio} ${'$'}{e.hora} (${ '$'}{e.duracion} min)")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showEventoModal) {
        AlertDialog(
            onDismissRequest = { showEventoModal = false },
            title = { Text("Agregar evento") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = nombre, onValueChange = { nombre = it }, placeholder = { Text("Nombre del evento") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = inicio, onValueChange = { inicio = it }, label = { Text("Fecha inicio (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = hora, onValueChange = { hora = it }, label = { Text("Hora (HH:mm)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = duracion, onValueChange = { duracion = it }, label = { Text("Duración (min)") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val d = duracion.toIntOrNull()
                    if (nombre.isNotBlank() && inicio.isNotBlank() && hora.isNotBlank() && d != null) {
                        AppState.agregarEvento(nombre.trim(), inicio, hora, d)
                        showEventoModal = false
                        nombre = ""; inicio = ""; hora = ""; duracion = ""
                    }
                }) { Text("Agregar") }
            },
            dismissButton = { TextButton(onClick = { showEventoModal = false; nombre = ""; inicio = ""; hora = ""; duracion = "" }) { Text("Cancelar") } }
        )
    }
}
