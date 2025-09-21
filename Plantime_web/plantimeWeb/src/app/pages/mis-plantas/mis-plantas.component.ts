import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-mis-plantas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './mis-plantas.component.html',
  styleUrl: './mis-plantas.component.css'
})
export class MisPlantasComponent { }
