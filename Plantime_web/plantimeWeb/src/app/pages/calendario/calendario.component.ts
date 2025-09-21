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

  constructor(public state: StateService) {}

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
