import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { StateService } from '../../services/state.service';

@Component({
  selector: 'app-panel-control',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './panel-control.component.html',
  styleUrl: './panel-control.component.css'
})
export class PanelControlComponent {
  modoViaje = false;
  modoTranquilidad = false;

  compartirUsuario = '';

  showSensorModal = false;
  nuevoSensorNombre = '';

  constructor(public state: StateService) {}

  toggleViaje() { this.modoViaje = !this.modoViaje; }
  toggleTranquilidad() { this.modoTranquilidad = !this.modoTranquilidad; }

  abrirModalSensor() { this.showSensorModal = true; }
  cerrarModalSensor() { this.showSensorModal = false; this.nuevoSensorNombre = ''; }
  agregarSensor() {
    const nombre = this.nuevoSensorNombre.trim();
    if (!nombre) return;
    this.state.agregarSensor(nombre);
    this.cerrarModalSensor();
  }

  compartirCuenta() {
    if (!this.compartirUsuario.trim()) return;
    // Aquí podríamos enviar al backend; por ahora, solo limpiamos el campo
    this.compartirUsuario = '';
    alert('Cuenta compartida');
  }
}
