import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IPig } from '../../../model/pig/pig.interface';
import { IWeight } from '../../../model/activity/weight.interface';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ValidationFormsService } from 'src/app/services/validation/validation-forms.service';

@Component({
  selector: 'app-weight-control',
  templateUrl: './weight-control.component.html',
  styleUrls: ['./weight-control.component.scss'],
})

export class WeightControlComponent implements OnInit {
  wh!: IWeight;
  pigSelected!: IPig;
  whRef!: IWeight;
  pigRef: string = '';
  weightHistory: IWeight[] = [];
  filteredWeightHistory: IWeight[] = [];

  formEditWeight!: FormGroup;
  formErrors: any;
  modalVisible: boolean = false;
  weightSubmitted: boolean = false;
  modalSubmitted: boolean = false;

  formAddWeight!: FormGroup;
  modalAddVisible: boolean = false;
  modalDelete: boolean = false;

  loading: boolean = false;

  avatar: string = './assets/img/avatars/pig.png';

  constructor(
    private restService: PigRestService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private validation: ValidationFormsService
  ) {}

  ngOnInit(): void {
    this.pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];
    this.getWeightHistory(this.pigRef);
    this.formEditWeight = this.formBuilder.group({
      weight: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.weightPattern),
        ],
      ],
      date: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.datePattern),
        ],
      ],
    });

    this.formAddWeight = this.formBuilder.group({
      weight: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.weightPattern),
        ],
      ],
      date: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.datePattern),
        ],
      ],
    });
  }

  showChart(): void {
    this.router.navigate(['/dashboard'], {
      queryParams: { pigRef: this.pigRef },
    });
  }

  // Variables to hold filter values
  dateFilter: string = '';
  weightFilter: string = '';

  // Function to apply filters
  applyFilters() {
    this.filteredWeightHistory = this.weightHistory.filter(
      (weight) =>
        (this.dateFilter === '' || weight.date === this.dateFilter) &&
        (this.weightFilter === '' || weight.weight.includes(this.weightFilter))
    );
  }

  getWeightHistory(pigId: string): void {
    this.loading = true;

    this.restService.getPigByID(pigId).subscribe((pig: IPig) => {
      this.pigSelected = pig;
      if (pig.weightHistory) {
        this.weightHistory = Object.values(pig.weightHistory);
        // Order weight history by date in descending order
        this.weightHistory.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
        this.filteredWeightHistory = this.weightHistory;
      } else {
        this.filteredWeightHistory = [];
      }

      this.loading = false;
    });
  }

  editWeight(wh: IWeight): void {
    this.wh = wh;
    this.modalVisible = true;
  }

  toggleModal(): void {
    this.modalVisible = !this.modalVisible;
  }

  handleModalChange(event: any) {
    this.modalVisible = event;
  }

  confirmEdit(): void {
    this.modalSubmitted = true;

    if (this.pigRef && this.formEditWeight.valid) {
      const updatedWeight: IWeight = {
        date: this.formEditWeight.get('date')?.value,
        weight: this.formEditWeight.get('weight')?.value,
      };

      // Encontrar o índice do peso a ser editado
      const index = this.weightHistory.findIndex(
        (wh) => wh.date === this.wh.date && wh.weight === this.wh.weight
      );

      if (index !== -1) {
        // Atualizar o peso no array weightHistory
        this.weightHistory[index] = updatedWeight;

        // Enviar a atualização para o serviço
        this.restService
          .updateWeightHistory(this.pigRef, this.weightHistory)
          .then(() => {
            this.toggleModal();
          })
          .catch((error) => {
            console.error('Error updating weight history:', error);
          });
      } else {
        console.error('Weight not found in the weight history array.');
      }
    }
  }

  cancelEdit(): void {
    this.toggleModal();
  }

  toggleAddWeight(): void {
    this.modalAddVisible = !this.modalAddVisible;
  }

  handleAddWeight(event: any) {
    this.modalAddVisible = event;
  }

  addWeightMode(pig: IPig): void {
    if (pig.key) {
      this.pigRef = pig.key;
    }
    this.toggleAddWeight();
  }

  addWeight(): void {
    this.weightSubmitted = true;
    if (this.pigRef) {
      this.restService.addWeightToPig(this.pigRef, this.formAddWeight.value);
      this.getWeightHistory(this.pigRef);
      this.toggleAddWeight();
    }
  }

  cancelAddWeight(): void {
    this.toggleAddWeight();
    this.pigRef = '';
  }

  toggleDeleteMode(): void {
    this.modalDelete = !this.modalDelete;
  }

  handleDeleteMode(event: any) {
    this.modalDelete = event;
  }

  deleteMode(wh: IWeight): void {
    this.whRef = wh;
    this.toggleDeleteMode();
  }

  confirmDelete(): void {
    this.modalSubmitted = true;

    // Verificar se os dados necessários estão disponíveis
    if (this.pigRef && this.whRef) {
      // Encontrar o índice do peso a ser excluído
      const index = this.weightHistory.findIndex((wh) => wh === this.whRef);

      if (index !== -1) {
        // Remover o peso do array weightHistory
        this.weightHistory.splice(index, 1);

        // Enviar a atualização para o serviço
        this.restService
          .updateWeightHistory(this.pigRef, this.weightHistory)
          .then(() => {
            this.toggleDeleteMode();
          })
          .catch((error) => {
            console.error('Error updating weight history:', error);
          });
      } else {
        console.error('Weight not found in the weight history array.');
      }
    } else {
      console.error('Required data not available for weight deletion.');
    }
  }

  cancelDelete(): void {
    this.toggleDeleteMode();
    this.whRef = {
      date: '',
      weight: '',
    };
  }
}
