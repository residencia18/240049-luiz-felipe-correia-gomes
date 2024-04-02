import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  formFields: { type: string, name: string, label: string }[] = [];
  formData: any = {};

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getFirstCountry().subscribe(data => {
      this.formFields = Object.keys(data).map(key => {
        return { type: 'text', name: key, label: key };
      });
    });
  }
}
