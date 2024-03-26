import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Treatment } from '../../model/treatment';
import { RestService } from '../../services/rest.service';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-treatment-result',
  templateUrl: './treatment-result.component.html',
  styleUrls: ['./treatment-result.component.css'],
})
export class TreatmentResultComponent implements OnInit {
  treatments: Treatment[] = [];
  displayedColumns: string[] = [
    'client',
    'animal',
    'service',
    'date',
    'observations',
    'actions',
  ];
  searchTerm: string = '';

  constructor(
    private restService: RestService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.pipe(
      switchMap(params => {
        this.searchTerm = params['searchTerm'];
        if (this.searchTerm) {
          return this.restService.searchItems(this.searchTerm);
        } else {
          return [];
        }
      })
    ).subscribe((treatments) => {
      this.treatments = treatments;
    });
  }

  editTreatment() {
    this.router.navigate(['/edicao']);
  }
}
