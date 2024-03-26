import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VehicleService {
  private readonly vehiclesUrl: string =  'https://raw.githubusercontent.com/lufecrx/residenciatic18-frontend/main/pages/assets/files/vehicles.json';

  constructor(private http: HttpClient) { }

  getVehiclesData() : Observable<any> {
    return this.http.get(this.vehiclesUrl);
  }
}
