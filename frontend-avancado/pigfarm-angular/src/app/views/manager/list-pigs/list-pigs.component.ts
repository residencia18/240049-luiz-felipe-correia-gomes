import { Component, OnInit } from '@angular/core';
import { IPig } from '../../../model/pig/pig.interface';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationFormsService } from 'src/app/services/validation/validation-forms.service';

@Component({
  selector: 'app-list-pigs',
  templateUrl: './list-pigs.component.html',
  styleUrls: ['./list-pigs.component.scss'],
})
export class ListPigsComponent implements OnInit {
  pigs: IPig[] = [];
  filteredPigs: IPig[] = [];
  currentPage: number = 1;
  pageSize: number = 10; // Número de itens por página
  totalItems: number = 0; // Total de itens

  modalWeight: boolean = false;
  modalDelete: boolean = false;

  formAddWeight!: FormGroup;
  formErrors: any;
  pigRef: string = '';
  weightSubmitted: boolean = false;

  loading: boolean = false;

  avatar: string = './assets/img/avatars/pig.png';

  constructor(
    private restService: PigRestService,
    private router: Router,
    private formBuilder: FormBuilder,
    private validation: ValidationFormsService,
  ) {}

  ngOnInit(): void {
    this.getPigs();

    this.formAddWeight = this.formBuilder.group({
      weight: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.weightPattern),
        ],
      ],
      date: ['',
        [
          Validators.required,
          Validators.pattern(
            this.validation.formRules.datePattern
          )
        ],
      ],
    });
  }

  // Variables to hold filter values
  fatherIdFilter: string = '';
  motherIdFilter: string = '';
  dateOfBirthFilter: string = '';
  dateOfExitFilter: string = '';
  genderFilter: string = '';
  statusFilter: string = '';

  // Function to apply filters
  applyFilters() {
    this.filteredPigs = this.pigs.filter(
      (pig) =>
        pig.father_id.includes(this.fatherIdFilter) &&
        pig.mother_id.includes(this.motherIdFilter) &&
        (this.dateOfBirthFilter === '' ||
          pig.date_birth === this.dateOfBirthFilter) &&
        (this.dateOfExitFilter === '' ||
          pig.date_exit === this.dateOfExitFilter) &&
        (this.genderFilter === '' || pig.gender === this.genderFilter) &&
        (this.statusFilter === '' || pig.status === this.statusFilter)
    );
  }

  getPigs(): void {
    this.loading = true;

    this.restService
      .getPigsPaginated(this.currentPage, this.pageSize)
      .subscribe((response) => {
        this.pigs = response;
        this.totalItems = response.length;
        this.filteredPigs = this.pigs;
        this.loading = false;
      });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.getPigs();
  }

  get totalPages(): number {
    return Math.ceil(this.totalItems / this.pageSize);
  }

  getPageNumbers(): number[] {
    return Array.from({ length: this.totalPages }, (_, index) => index + 1);
  }

  editMode(pig: IPig): void {
    this.router.navigate(['/manager/edit-pig'], {
      queryParams: { pigRef: pig.key },
    });
  }

  chartMode(pig: IPig): void {
    this.router.navigate(['/dashboard'], {
      queryParams: { pigRef: pig.key },
    });
  }

  weightControlMode(pig: IPig): void {
    this.router.navigate(['/manager/weight-control'], {
      queryParams: { pigRef: pig.key },
    });
  }

  toggleDeleteMode(): void {
    this.modalDelete = !this.modalDelete;
  }

  handleDeleteMode(event: any) {
    this.modalDelete = event;
  }


  deleteMode(pig: IPig): void {
    if (pig.key) {
      this.pigRef = pig.key;
    }
    this.toggleDeleteMode();
  }

  deletePig(): void {
    if (this.pigRef) {
      this.restService.deletePig(this.pigRef);
      this.getPigs();
      this.toggleDeleteMode();
      this.pigRef = '';
    }
  }

  cancelDelete(): void {
    this.toggleDeleteMode();
    this.pigRef = '';
  }

  toggleAddWeight(): void {
    this.modalWeight = !this.modalWeight;
  }

  handleAddWeight(event: any) {
    this.modalWeight = event;
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
      this.getPigs();
      this.toggleAddWeight();
      this.pigRef = '';
    }
  }

  cancelAddWeight(): void {
    this.toggleAddWeight();
    this.pigRef = '';
  }
}
