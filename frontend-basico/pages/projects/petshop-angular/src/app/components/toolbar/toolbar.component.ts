import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { MatMenuTrigger } from '@angular/material/menu';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.css',
})
export class ToolbarComponent {
  @ViewChild(MatMenuTrigger) menuTrigger!: MatMenuTrigger;

  constructor(private authService: AuthService) {}

  isAuthenticated() {
    return AuthService.isAuthenticated();
  }

  logout() {
    this.authService.logout();
  }
}
