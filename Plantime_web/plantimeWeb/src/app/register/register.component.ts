import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  Usuario: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  acceptTerms: boolean = false;
  errorMessage: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    // Validaciones
    if (!this.Usuario || !this.email || !this.password || !this.confirmPassword) {
      this.errorMessage = 'Por favor, completa todos los campos';
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden';
      return;
    }

    if (this.password.length < 6) {
      this.errorMessage = 'La contraseña debe tener al menos 6 caracteres';
      return;
    }

    // Validación de email básica
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.email)) {
      this.errorMessage = 'Por favor, ingresa un correo electrónico válido';
      return;
    }

    // Aquí iría la lógica de registro
    console.log('Datos de registro:', {
      Usuario: this.Usuario,
      email: this.email,
      password: this.password
    });

    // Simulación de registro exitoso
    this.errorMessage = '';
    alert('Registro exitoso! Ahora puedes iniciar sesión.');

    // Redirigir al login
    this.router.navigate(['/login']);
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
