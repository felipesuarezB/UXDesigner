import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

interface PlantaToggles {
  temperatura: boolean;
  humedad: boolean;
  luz: boolean;
}

interface PlantaNotas {
  [planta: string]: string[];
}

@Component({
  selector: 'app-mis-plantas',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './mis-plantas.component.html',
  styleUrl: './mis-plantas.component.css'
})
export class MisPlantasComponent {
  plantas = [
    { nombre: 'Aloe Vera', sensor: 'Patio', icono: '🌿' },
    { nombre: 'Hierbabuena', sensor: 'Cocina', icono: '🌱' }
  ];
  toggles: { [planta: string]: PlantaToggles } = {};
  notas: PlantaNotas = {};
  showNotasModal: string|null = null;
  nuevaNota: string = '';

  constructor() {
    this.plantas.forEach(p => {
      this.toggles[p.nombre] = { temperatura: false, humedad: false, luz: false };
      this.notas[p.nombre] = [];
    });
  }

  toggle(planta: string, tipo: keyof PlantaToggles) {
    this.toggles[planta][tipo] = !this.toggles[planta][tipo];
  }

  abrirNotas(planta: string) {
    this.showNotasModal = planta;
    this.nuevaNota = '';
  }

  cerrarNotas() {
    this.showNotasModal = null;
    this.nuevaNota = '';
  }

  agregarNota(planta: string) {
    if (this.nuevaNota.trim()) {
      this.notas[planta].push(this.nuevaNota.trim());
      this.nuevaNota = '';
    }
  }

  eliminarNota(planta: string, idx: number) {
    this.notas[planta].splice(idx, 1);
  }
}
