import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CategoryComponent } from '../category/category.component';
import { PropertiesComponent } from '../properties/properties.component';
import { ValueComponent } from '../value/value.component';
import { VehiclesComponent } from '../vehicles/vehicles.component';
import { ManagerDataService } from '../../services/manager-data.service';
import { Subscription } from 'rxjs';

enum VehicleCategory {
  Airplanes = 'Airplanes',
  Cars = 'Cars',
  Ships = 'Ships',
}

interface Vehicle {
  Name: string;
  Model: string;
  Engine: string;
  NumberOfPassengers: number | string;
  Autonomia: string;
  Alcance: string;
  Teto?: string; // Optional property
}

@Component({
  selector: 'app-menu-category',
  standalone: true,
  imports: [CommonModule, HttpClientModule, CategoryComponent, PropertiesComponent, ValueComponent, VehiclesComponent],
  templateUrl: './menu-category.component.html',
  styleUrl: './menu-category.component.css'
})
export class MenuCategoryComponent implements OnInit {
  VehicleCategory!: VehicleCategory;

  constructor(private managerDataService: ManagerDataService) { }

  categoryList: string[] = [];
  vehiclesList: Vehicle[] = [];
  vehicleSelected: any;
  valueProperty: any;

  private categoryListSubscription: Subscription | undefined;
  private vehiclesListSubscription: Subscription | undefined;
  private vehicleSelectedSubscription: Subscription | undefined;
  private valuePropertySubscription: Subscription | undefined;

  ngOnInit() {
    this.load();

    this.categoryListSubscription = this.managerDataService.categoryList$.subscribe(categories => {
      this.categoryList = categories;
    });

    this.vehiclesListSubscription = this.managerDataService.vehiclesList$.subscribe(vehicles => {
      this.vehiclesList = vehicles;
    });

    this.vehicleSelectedSubscription = this.managerDataService.vehicleSelected$.subscribe(selectedVehicle => {
      this.vehicleSelected = selectedVehicle;
    });

    this.valuePropertySubscription = this.managerDataService.valueProperty$.subscribe(value => {
      this.valueProperty = value;
    });
  }

  load() {
    this.managerDataService.loadCategoryList();
  }

  filterByCategory(category: string) {
    this.managerDataService.filterByCategory(category);
  }

  selectVehicle(vehicle: any) {
    this.managerDataService.selectVehicle(vehicle);
  }

  selectProperty(property: string) {
    this.managerDataService.selectProperty(property);
  }

  getObjectKeys(obj: any): string[] {
    return this.managerDataService.getObjectKeys(obj);
  }

  ngOnDestroy() {
    this.categoryListSubscription?.unsubscribe();
    this.vehiclesListSubscription?.unsubscribe();
    this.vehicleSelectedSubscription?.unsubscribe();
    this.valuePropertySubscription?.unsubscribe();
  }
}

