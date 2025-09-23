import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usuario: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    if (!this.usuario || !this.password) {
      this.errorMessage = 'Por favor, completa todos los campos';
      return;
    }

    // Redirigir al menú de mis plantas
    this.router.navigate(['/mis-plantas']);
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}
