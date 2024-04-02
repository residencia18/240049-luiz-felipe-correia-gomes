import { Component } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ValidationFormsService } from 'src/app/services/validation/validation-forms.service';

/** passwords must match */
export class PasswordValidators {
  static confirmPassword(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password');
    const confirm = control.get('confirmPassword');
    if (password?.valid && password?.value === confirm?.value) {
      confirm?.setErrors(null);
      return null;
    }
    confirm?.setErrors({ passwordMismatch: true });
    return { passwordMismatch: true };
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  formSignup!: FormGroup;
  submitted = false;
  formErrors: any;
  formControls!: string[];

  constructor(
    private router: Router,
    private authService: AuthService,
    private readonly formBuilder: FormBuilder,
    private validationService: ValidationFormsService
  ) {
    this.formErrors = this.validationService.errorMessages;
  }

  ngOnInit(): void {
    if (AuthService.isAuthenticated()) {
      this.authService.logout();
    }
    this.buildForm();
  }

  buildForm(): void {
    this.formSignup = this.formBuilder.group(
      {
        email: ['', [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(this.validationService.formRules.passwordMin),
            Validators.pattern(
              this.validationService.formRules.passwordPattern
            ),
          ],
        ],
        confirmPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(this.validationService.formRules.passwordMin),
            Validators.pattern(
              this.validationService.formRules.passwordPattern
            ),
          ],
        ],
      },
      { validators: [PasswordValidators.confirmPassword] },
    );
  }

  signup() {
    this.submitted = true;
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
