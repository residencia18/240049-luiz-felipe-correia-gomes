import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin!: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private readonly formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(): void {
    this.formLogin = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
    });
  }

  login() {
    if (this.formLogin.invalid) {
      return;
    }
    this.authService.login(this.formLogin.value)
      .then(() => {
        this.router.navigate(['/']);
      })
  }

  getErrorMessage(): string {
    return this.authService.getErrorMessage();
  }

  clearErrorMessage() {
    this.authService.errorMessage = '';
  }
}
