import { Injectable, signal } from '@angular/core';

export interface Planta {
  id: number;
  nombre: string;
  sensor: string;
  icono: string; // emoji por ahora
}

export interface Sensor {
  id: number;
  nombre: string;
}

export interface EventoCalendario {
  id: number;
  nombre: string;
  inicio: string; // ISO date
  hora: string;   // HH:mm
  duracion: number; // minutos
}

@Injectable({ providedIn: 'root' })
export class StateService {
  plantas = signal<Planta[]>([
    { id: 1, nombre: 'Aloe Vera', sensor: 'Patio', icono: '🌿' },
    { id: 2, nombre: 'Hierbabuena', sensor: 'Cocina', icono: '🌱' },
  ]);

  sensores = signal<Sensor[]>([
    { id: 1, nombre: 'Patio' },
    { id: 2, nombre: 'Cocina' },
    { id: 3, nombre: 'Habitación' },
  ]);

  eventos = signal<EventoCalendario[]>([
    { id: 1, nombre: 'Riego Aloe', inicio: new Date().toISOString().slice(0,10), hora: '08:00', duracion: 30 },
  ]);

  private sensorId = 100;
  private eventoId = 1000;

  agregarSensor(nombre: string) {
    const nuevo = { id: ++this.sensorId, nombre };
    this.sensores.update(list => [...list, nuevo]);
  }

  agregarEvento(nombre: string, inicio: string, hora: string, duracion: number) {
    const nuevo = { id: ++this.eventoId, nombre, inicio, hora, duracion };
    this.eventos.update(list => [...list, nuevo]);
  }
}
