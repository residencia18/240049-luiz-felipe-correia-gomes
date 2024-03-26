import { Component, OnInit } from '@angular/core';
import { Treatment } from '../../model/treatment';
import { RestService } from '../../services/rest.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-treatment-list',
  templateUrl: './treatment-list.component.html',
  styleUrl: './treatment-list.component.css'
})

export class TreatmentListComponent implements OnInit {
  treatments: Treatment[] = [];
  displayedColumns: string[] = ['client', 'animal', 'service', 'date', 'observations', 'actions']; // Adicione esta linha

  constructor(private restService: RestService, private router: Router) {}

  ngOnInit(): void {
    this.restService.getItems().subscribe((treatments) => {
      this.treatments = treatments;
    })
  }

  editTreatment() {
    this.router.navigate(['/edicao']);
  }
}
