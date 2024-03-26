import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-value',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './value.component.html',
  styleUrl: './value.component.css'
})
export class ValueComponent {
  @Input() valueProperty: any;
}
