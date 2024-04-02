import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PigRestService } from '../../../services/rest/pig-rest.service';
import { ValidationFormsService } from '../../../services/validation/validation-forms.service';

@Component({
  selector: 'app-register-pig',
  templateUrl: './register-pig.component.html',
  styleUrl: './register-pig.component.scss',
})
export class RegisterPigComponent implements OnInit {
  formPig!: FormGroup;
  submitted = false;
  formErrors: any;
  formControls!: string[];

  constructor(
    private readonly formBuilder: FormBuilder,
    private router: Router,
    private validationService: ValidationFormsService,
    private restService: PigRestService,
    )
  {
    this.formErrors = this.validationService.errorMessages;
  }

  ngOnInit(): void {
    this.formPig = this.formBuilder.group({
      identifier: [
          '',
          [
            Validators.required,
            Validators.pattern(
              this.validationService.formRules.identifierPattern
            ),
          ],
      ],
      father_id: [
          '',
          [
            Validators.required,
            Validators.pattern(
              this.validationService.formRules.identifierPattern
            ),
          ],
      ],
      mother_id: [
          '',
          [
            Validators.required,
            Validators.pattern(
              this.validationService.formRules.identifierPattern
            ),
          ],
      ],
      date_birth: [
          '',
          [
            Validators.required,
            Validators.pattern(this.validationService.formRules.datePattern),
          ],
      ],
      date_exit: [
          '',
          [
            Validators.required,
            Validators.pattern(this.validationService.formRules.datePattern),
          ],
      ],
      status: [
          '',
          [
            Validators.required,
          ],
        ],
      gender:
        [
          '',
          [
            Validators.required,
          ],
        ],
    });
    this.formControls = Object.keys(this.formPig.controls);
  }

  async onSubmit() {
    this.submitted = true;
    if (this.formPig.valid) {
      try {
        const newPig = this.formPig.value;
        await this.restService.addPig(newPig);
        this.router.navigate(['/manager/list-pigs']);
      } catch (error : any) {
        console.error('Erro ao enviar o formulário:', error);
      }
    } else {
      console.log('Formulário inválido');
    }
  }
}
