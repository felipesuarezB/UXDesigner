import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-planta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-planta.component.html',
  styleUrl: './crear-planta.component.css'
})
export class CrearPlantaComponent {
  constructor(private router: Router) {}

  cancelar() {
    this.router.navigate(['/mis-plantas']);
  }
}
