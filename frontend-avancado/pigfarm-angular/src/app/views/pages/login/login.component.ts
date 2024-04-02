import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationFormsService } from 'src/app/services/validation/validation-forms.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { trigger, transition, style, animate } from '@angular/animations';
import { UserLogin } from 'src/app/model/user/user.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('300ms ease-out', style({ opacity: 1 })),
      ]),
      transition(':leave', [
        animate('300ms ease-in', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class LoginComponent implements OnInit {

  formLogin!: FormGroup;
  formErrors: any;
  errorMessage: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private readonly formBuilder: FormBuilder,
    private validationService: ValidationFormsService
  ) {
    this.formErrors = this.validationService.errorMessages;
  }

  ngOnInit(): void {
    this.errorMessage = false;
    this.buildForm();
  }

  buildForm(): void {
    this.formLogin = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['',
        [
          Validators.required,
          Validators.minLength(this.validationService.formRules.passwordMin),
          Validators.pattern(this.validationService.formRules.passwordPattern)
        ]
      ]
    });
  }

  login() {
    if (this.formLogin.invalid) {
      this.errorMessage = true;
      return;
    }

    const userLogin: UserLogin = this.formLogin.value;

    this.authService.login(userLogin)
      .then(() => {
        this.router.navigate(['/']);
      }).catch((error) => {
        this.errorMessage = true;
      })
  }

  getErrorMessage(): string {
    return this.authService.getErrorMessage();
  }

  clearErrorMessage() {
    this.authService.errorMessage = '';
  }
}
