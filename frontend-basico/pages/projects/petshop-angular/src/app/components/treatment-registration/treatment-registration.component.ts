import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RestService } from '../../services/rest.service';
import { FormInteractionsService } from '../../services/form-interaction.service';

@Component({
  selector: 'app-treatment-registration',
  templateUrl: './treatment-registration.component.html',
  styleUrl: './treatment-registration.component.css',
})

export class TreatmentRegistrationComponent implements OnInit {
  form!: FormGroup;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private restService: RestService,
    private router: Router,
    private formInteractionsService: FormInteractionsService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      client: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]+$/)]],
      animal: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]+$/)]],
      service: ['', Validators.required],
      date: [
        '',
        [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)],
      ],
      observations: [''],
    });

    // Assinando os Observables valuesChanges e statusChanges para monitorar as alterações
    this.form.valueChanges.subscribe((changes) => {
      this.formInteractionsService.updateFormChanges({ values: changes });
    });

    this.form.statusChanges.subscribe((status) => {
      this.formInteractionsService.updateFormChanges({ status: status });
    });
  }

  async onSubmit() {
    if (this.form.valid) {
      try {
        const newTreatment = this.form.value;
        await this.restService.addItem(newTreatment);
        this.router.navigate(['/listagem']);
      } catch (error : any) {
        console.error('Erro ao enviar o formulário:', error);
        this.errorMessage = error.message;
      }
    } else {
      console.log('Formulário inválido');
    }
  }
}
