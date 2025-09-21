import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./login/login.component').then(c => c.LoginComponent) },
  { path: 'register', loadComponent: () => import('./register/register.component').then(c => c.RegisterComponent) },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'dashboard', loadComponent: () => import('./pages/dashboard/dashboard.component').then(c => c.DashboardComponent) },
      { path: 'mis-plantas', loadComponent: () => import('./pages/mis-plantas/mis-plantas.component').then(c => c.MisPlantasComponent) },
      { path: 'mis-plantas/estadisticas', loadComponent: () => import('./pages/estadisticas/estadisticas.component').then(c => c.EstadisticasComponent) },
      { path: 'crear-planta', loadComponent: () => import('./pages/crear-planta/crear-planta.component').then(c => c.CrearPlantaComponent) },
      { path: 'comunidad', loadComponent: () => import('./pages/comunidad/comunidad.component').then(c => c.ComunidadComponent) },
      { path: 'historial', loadComponent: () => import('./pages/historial/historial.component').then(c => c.HistorialComponent) },
      { path: 'panel-control', loadComponent: () => import('./pages/panel-control/panel-control.component').then(c => c.PanelControlComponent) },
      { path: 'panel-control/calendario', loadComponent: () => import('./pages/calendario/calendario.component').then(c => c.CalendarioComponent) }
    ]
  },

  { path: '**', redirectTo: '/login' }
];
