import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  formSignup!: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private readonly formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (AuthService.isAuthenticated()) {
      this.authService.logout();
    }
    this.buildForm();
  }

  buildForm(): void {
    this.formSignup = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
        ],
      ],
    });
  }

  signup() {
    if (this.formSignup.invalid) {
      return;
    }
    this.authService.register(this.formSignup.value).then(() => {
      this.router.navigate(['/']);
    });
  }

  getErrorMessage(): string {
    return this.authService.getErrorMessage();
  }

  clearErrorMessage() {
    this.authService.errorMessage = '';
  }
}
