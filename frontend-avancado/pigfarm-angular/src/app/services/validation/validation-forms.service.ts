import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidationFormsService {

  errorMessages: any;

  formRules = {
    nonEmpty: '^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$',
    usernameMin: 6,
    passwordMin: 4,
    passwordPattern: '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}',
    datePattern: /^\d{4}-\d{2}-\d{2}$/,
    // statusOptions: ['Alive', 'Dead', 'Sold'],
    // genderOptions: ['Male', 'Female'],
    identifierPattern: '^[0-9]+$',
    weightPattern: '^(\\d+\\.?\\d*)$',
  };

  formErrors = {
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    birthday: '',
    identifier: '',
    father_id: '',
    mother_id: '',
    date_birth: '',
    date_exit: '',
    status: '',
    gender: '',
    weight: '',
    date: '',
    accept: false
  };

  constructor() {
    this.errorMessages = {
      firstName: {
        required: 'First name is required'
      },
      lastName: {
        required: 'Last name is required'
      },
      username: {
        required: 'Username is required',
        minLength: `Username must be ${this.formRules.usernameMin} characters or more`,
        pattern: 'Must contain letters and/or numbers, no trailing spaces'
      },
      email: {
        required: 'required',
        email: 'Invalid email address'
      },
      password: {
        required: 'Password is required',
        pattern: 'Password must contain: numbers, uppercase and lowercase letters',
        minLength: `Password must be at least ${this.formRules.passwordMin} characters`
      },
      confirmPassword: {
        required: 'Password confirmation is required',
        passwordMismatch: 'Passwords must match'
      },
      birthday: {
        required: 'Birthday date required'
      },
      accept: {
        requiredTrue: 'You have to accept our Terms and Conditions'
      },
      identifier: {
        required: 'Identifier is required',
        pattern: 'Identifier must contain only numbers'
      },
      father_id: {
        required: 'Father ID is required',
        pattern: 'Father ID must contain only numbers'
      },
      mother_id: {
        required: 'Mother ID is required',
        pattern: 'Mother ID must contain only numbers'
      },
      date_birth: {
        required: 'Date of birth is required',
        pattern: 'Invalid date format. Please use dd/mm/yyyy format'
      },
      date_exit: {
        required: 'Date of exit is required',
        pattern: 'Invalid date format. Please use dd/mm/yyyy format'
      },
      status: {
        required: 'Status is required',
        // inOptions: `Status must be one of the following: ${this.formRules.statusOptions.join(', ')}`
      },
      gender: {
        required: 'Gender is required',
        // inOptions: `Gender must be one of the following: ${this.formRules.genderOptions.join(', ')}`
      },
      weight: {
        required: 'Weight is required',
        pattern: 'Weight must be a number valid'
      },
      date: {
        required: 'Date is required',
        pattern: 'Invalid date format. Please use dd/mm/yyyy format'
      }
    };
  }
}


// export function inOptionsValidator(options: string[]): ValidatorFn {
//   return (control: AbstractControl): ValidationErrors | null => {
//     const value = control.value;
//     if (options.indexOf(value) === -1) {
//       return { inOptions: true };
//     }
//     return null;
//   };
// }
