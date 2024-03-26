import { Injectable } from '@angular/core';
import { VehicleService } from './vehicle.service';
import { BehaviorSubject } from 'rxjs';

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

@Injectable({
  providedIn: 'root',
})
export class ManagerDataService {
  private categoryListSubject = new BehaviorSubject<string[]>([]);
  categoryList$ = this.categoryListSubject.asObservable();

  private vehiclesListSubject = new BehaviorSubject<Vehicle[]>([]);
  vehiclesList$ = this.vehiclesListSubject.asObservable();

  private vehicleSelectedSubject = new BehaviorSubject<any>(null);
  vehicleSelected$ = this.vehicleSelectedSubject.asObservable();

  private valuePropertySubject = new BehaviorSubject<any>(null);
  valueProperty$ = this.valuePropertySubject.asObservable();

  constructor(private vehiclesService: VehicleService) {}

  loadCategoryList() {
    this.vehiclesService.getVehiclesData().subscribe(
      (result: any) => {
        if (result.data) {
          this.categoryListSubject.next(Object.keys(result.data));
        } else {
          console.error("Propriedade 'data' não encontrada no objeto de dados: ", result);
        }
      },
      (error) => {
        console.error("Erro ao carregar a lista de categorias: ", error);
      }
    );
  }

  filterByCategory(category: string) {
    this.vehiclesService.getVehiclesData().subscribe(
      (result: any) => {
        this.vehiclesListSubject.next(result.data[category] || []);
        this.vehicleSelectedSubject.next(null);
        this.valuePropertySubject.next(null);
      },
      (error) => {
        console.log("Erro ao carregar dados da categoria: ", error);
      }
    );
  }

  selectVehicle(vehicle: any) {
    this.vehicleSelectedSubject.next(vehicle);
    this.valuePropertySubject.next(null);
  }

  selectProperty(property: string) {
    this.valuePropertySubject.next(this.vehicleSelectedSubject.value ? this.vehicleSelectedSubject.value[property] : null);
  }

  getObjectKeys(obj: any): string[] {
    return Object.keys(obj);
  }
}
