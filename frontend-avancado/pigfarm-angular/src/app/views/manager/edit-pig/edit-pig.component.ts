import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PigRestService } from '../../../services/rest/pig-rest.service';
import { ValidationFormsService } from '../../../services/validation/validation-forms.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-pig',
  templateUrl: './edit-pig.component.html',
  styleUrl: './edit-pig.component.scss',
})
export class EditPigComponent implements OnInit {
  formPig!: FormGroup;
  submitted = false;
  formErrors: any;
  formControls!: string[];
  pigRef: string = '';

  constructor(
    private readonly formBuilder: FormBuilder,
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private validationService: ValidationFormsService,
    private restService: PigRestService,
    )
  {
    this.formErrors = this.validationService.errorMessages;
  }

  ngOnInit(): void {
    this.pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];

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
        await this.restService.updatePig(this.pigRef, newPig);
        this.route.navigate(['/manager/list-pigs']);
      } catch (error : any) {
        console.error('Erro ao enviar o formulário:', error);
      }
    } else {
      console.log('Formulário inválido');
    }
  }
}
