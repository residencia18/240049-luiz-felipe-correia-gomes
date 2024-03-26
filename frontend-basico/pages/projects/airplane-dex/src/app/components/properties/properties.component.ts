import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { JumpingEffectDirective } from '../../directives/jumping-effect.directive';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [CommonModule, JumpingEffectDirective],
  templateUrl: './properties.component.html',
  styleUrls: ['./properties.component.css']
})
export class PropertiesComponent {
  @Input() vehicleSelected: any;
  @Output() propertySelected = new EventEmitter<string>();

  selectProperty(property: string) {
    this.propertySelected.emit(property);
  }

  getObjectKeys(obj: any): string[] {
    return Object.keys(obj);
  }
}
