package com.example.plantimeapp.ui.state

import androidx.compose.runtime.mutableStateListOf

object AppState {
    data class Evento(
        val nombre: String,
        val inicio: String, // YYYY-MM-DD
        val hora: String,   // HH:mm
        val duracion: Int   // minutos
    )

    private val _sensores = mutableStateListOf("Patio", "Cocina")
    private val _eventos = mutableStateListOf<Evento>()

    fun sensores(): List<String> = _sensores
    fun eventos(): List<Evento> = _eventos

    fun agregarSensor(nombre: String) { _sensores.add(nombre) }
    fun agregarEvento(nombre: String, inicio: String, hora: String, duracion: Int) {
        _eventos.add(Evento(nombre, inicio, hora, duracion))
    }
}
