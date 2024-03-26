import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms'; // Importar FormGroup, FormBuilder e Validators
import { Treatment } from '../../model/treatment';
import { RestService } from '../../services/rest.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-treatment-edit',
  templateUrl: './treatment-edit.component.html',
  styleUrls: ['./treatment-edit.component.css'],
})
export class TreatmentEditComponent implements OnInit {
  treatments: Treatment[] = [];
  displayedColumns: string[] = [
    'client',
    'animal',
    'service',
    'date',
    'observations',
  ];

  // Declaração do FormGroup
  editForm!: FormGroup;

  constructor(
    private restService: RestService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadTreatments();
    // Inicializar o formulário reativo
    this.initForm();
  }

  loadTreatments(): void {
    this.restService.getItems().subscribe((treatments) => {
      this.treatments = treatments.map((treatment) => ({
        ...treatment,
        originalValues: { ...treatment },
        editing: false // Inicializando o modo de edição como false
      }));
    });
  }


  // Método para inicializar o formulário reativo
  initForm(): void {
    this.editForm = this.formBuilder.group({
      client: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]+$/)]],
      animal: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]+$/)]],
      service: ['', Validators.required],
      date: [
        '',
        [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)],
      ],
      observations: [''],
    });
  }

  toggleEditMode(treatment: Treatment): void {
    treatment.editing = !treatment.editing;
    // Preencher os valores do formulário com os valores atuais do tratamento
    if (treatment.editing) {
      this.editForm.setValue({
        client: treatment.client,
        animal: treatment.animal,
        service: treatment.service,
        date: treatment.date,
        observations: treatment.observations,
      });
    }
  }

  saveChanges(treatment: Treatment): void {
    // Atualizar os valores do tratamento com os valores do formulário
    treatment.client = this.editForm.value.client;
    treatment.animal = this.editForm.value.animal;
    treatment.service = this.editForm.value.service;
    treatment.date = this.editForm.value.date;
    treatment.observations = this.editForm.value.observations;

    // Salvar as alterações usando o serviço
    if (treatment.key !== undefined) {
      this.restService.updateItem(treatment.key, treatment);
      this.toggleEditMode(treatment);
      this.loadTreatments();
      this.router.navigate(['/listagem']);
    }
  }

  cancelEdit(treatment: Treatment): void {
    this.toggleEditMode(treatment);
    // Restaurar os valores originais do tratamento
    Object.assign(treatment, treatment.originalValues);
  }
}
