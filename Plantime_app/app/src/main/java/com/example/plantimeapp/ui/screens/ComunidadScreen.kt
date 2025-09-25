package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantimeapp.ui.theme.GreenDark

@Composable
fun ComunidadScreen() {
    var temaSeleccionado by remember { mutableStateOf<String?>(null) }
    val temas = remember { listOf("Riego", "Sustratos", "Iluminación", "Plagas") }
    val comentarios = remember {
        mutableStateMapOf(
            "Riego" to mutableStateListOf(
                "Usen agua a temperatura ambiente",
                "Evitar encharcamientos"
            ),
            "Sustratos" to mutableStateListOf("Mezcla de perlita y turba funciona bien"),
            "Iluminación" to mutableStateListOf("Luz indirecta para suculentas pequeñas"),
            "Plagas" to mutableStateListOf("Revisar hojas inferiores semanalmente"),
        )
    }
    var nuevoComentario by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Comunidad", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxSize()) {
            // Comentarios
            Card(Modifier.weight(2f)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Comentarios", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(8.dp))
                    if (temaSeleccionado == null) {
                        Text("Selecciona un tema a la derecha", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    } else {
                        Text(temaSeleccionado!!, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        comentarios[temaSeleccionado!!]?.forEach { c ->
                            Text("• $c")
                        }
                        Spacer(Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = nuevoComentario,
                                onValueChange = { nuevoComentario = it },
                                placeholder = { Text("Agrega tu comentario") },
                                modifier = Modifier.weight(1f)
                            )
                            Button(onClick = {
                                val t = temaSeleccionado
                                if (t != null && nuevoComentario.isNotBlank()) {
                                    (comentarios[t] ?: mutableStateListOf()).add(nuevoComentario.trim())
                                    comentarios.putIfAbsent(t, mutableStateListOf(nuevoComentario.trim()))
                                    nuevoComentario = ""
                                }
                            }, colors = ButtonDefaults.buttonColors(containerColor = GreenDark)) {
                                Text("Agregar")
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.width(12.dp))

            // Temas
            Card(Modifier.weight(1f)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Temas de interés", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        temas.forEach { t ->
                            Button(
                                onClick = { temaSeleccionado = t },
                                colors = ButtonDefaults.buttonColors(containerColor = GreenDark)
                            ) { Text(t) }
                        }
                    }
                }
            }
        }
    }
}
