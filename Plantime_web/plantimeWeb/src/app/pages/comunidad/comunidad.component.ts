import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-comunidad',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './comunidad.component.html',
  styleUrl: './comunidad.component.css'
})
export class ComunidadComponent {
  temas = ['Riego', 'Sustratos', 'Iluminación', 'Plagas'];
  temaSeleccionado: string | null = null;
  comentarios: Record<string, string[]> = {
    'Riego': ['Usen agua a temperatura ambiente', 'Evitar encharcamientos'],
    'Sustratos': ['Mezcla de perlita y turba funciona bien'],
    'Iluminación': ['Luz indirecta para suculentas pequeñas'],
    'Plagas': ['Revisar hojas inferiores semanalmente']
  };
  nuevoComentario = '';

  seleccionarTema(t: string) { this.temaSeleccionado = t; }
  agregarComentario() {
    if (!this.temaSeleccionado || !this.nuevoComentario.trim()) return;
    const list = this.comentarios[this.temaSeleccionado] ||= [];
    list.push(this.nuevoComentario.trim());
    this.nuevoComentario = '';
  }
}
