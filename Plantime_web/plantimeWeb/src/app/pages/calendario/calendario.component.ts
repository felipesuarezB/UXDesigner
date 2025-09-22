import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StateService } from '../../services/state.service';

@Component({
  selector: 'app-calendario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './calendario.component.html',
  styleUrls: ['./calendario.component.css']
})
export class CalendarioComponent {
  showEventoModal = false;
  nombre = '';
  inicio = '';
  fin = '';

  mesActual: string;
  anioActual: number;
  mesIndex: number;
  diasDelMes: number[] = [];

  meses = [
    'enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio',
    'julio', 'agosto', 'septiembre', 'octubre', 'noviembre', 'diciembre'
  ];

  constructor(public state: StateService) {
    const fecha = new Date();
    this.mesIndex = fecha.getMonth();
    this.anioActual = fecha.getFullYear();
    this.mesActual = this.meses[this.mesIndex];
    this.actualizarDiasDelMes();
  }

  cambiarMes(delta: number) {
    this.mesIndex += delta;
    if (this.mesIndex < 0) {
      this.mesIndex = 11;
      this.anioActual--;
    } else if (this.mesIndex > 11) {
      this.mesIndex = 0;
      this.anioActual++;
    }
    this.mesActual = this.meses[this.mesIndex];
    this.actualizarDiasDelMes();
  }

  actualizarDiasDelMes() {
    const dias = new Date(this.anioActual, this.mesIndex + 1, 0).getDate();
    this.diasDelMes = Array.from({length: dias}, (_, i) => i + 1);
  }

  abrirModal() { this.showEventoModal = true; }
  cerrarModal() { this.showEventoModal = false; this.nombre=''; this.inicio=''; this.fin=''; }
  agregarEvento() {
    if (!this.nombre.trim() || !this.inicio || !this.fin) return;
    this.state.agregarEvento(this.nombre.trim(), this.inicio, this.fin);
    this.cerrarModal();
  }

  // Helper to check if there is at least one event that starts on the given day (1..31)
  hasEventOnDay(d: number): boolean {
    const eventos = this.state.eventos();
    return eventos.some(e => Number(e.inicio.slice(8, 10)) === d);
  }
}
