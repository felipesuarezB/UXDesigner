import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-historial',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historial.component.html',
  styleUrl: './historial.component.css'
})
export class HistorialComponent {
  registros = [
    'Se creó planta: Aloe Vera',
    'Se configuró sensor: Patio',
    'Se agregó evento de calendario: Riego Aloe',
  ];
}
