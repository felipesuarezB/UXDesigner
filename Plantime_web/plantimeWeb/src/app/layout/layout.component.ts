import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterOutlet],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  constructor(private router: Router) {}

  isActive(segment: string): boolean {
    const url = this.router.url;
    if (segment === 'mis-plantas') {
      if (url === '/crear-planta' || url.startsWith('/crear-planta/')) return true;
    }
    return url === `/${segment}` || url.startsWith(`/${segment}/`);
  }
}
