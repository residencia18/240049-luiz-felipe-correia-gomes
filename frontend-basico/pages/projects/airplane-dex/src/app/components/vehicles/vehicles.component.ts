import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { JumpingEffectDirective } from '../../directives/jumping-effect.directive';
import { RotatingBackgroundColorDirective } from '../../directives/rotating-background-color.directive';

@Component({
  selector: 'app-vehicles',
  standalone: true,
  imports: [CommonModule, JumpingEffectDirective, RotatingBackgroundColorDirective],
  templateUrl: './vehicles.component.html',
  styleUrl: './vehicles.component.css'
})
export class VehiclesComponent {
  @Input() vehiclesList: any[] = [];
  @Output() vehicleSelected = new EventEmitter<any>();

  selectVehicle(vehicle: any) {
    this.vehicleSelected.emit(vehicle);
  }
}
