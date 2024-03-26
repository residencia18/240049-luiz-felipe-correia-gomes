import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

export interface WeatherElement {
  temperature: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  
  private apiUrl = "https://api.openweathermap.org/data/2.5/weather?"
  private pubKey = "7fc91c2469796840169634db0fb6a0fa";
  private lat = "-14.796708";
  private lon = "-39.173384";

  constructor(private http: HttpClient) { }

  getWeather() : Observable <any> {
    const url = `${this.apiUrl}lat=${this.lat}&lon=${this.lon}&appid=${this.pubKey}&units=metric&lang=pt_br`;

    return this.http.get<any>(url).pipe(map(data => ({
      temperature: data.main.temp,
      description: data.weather[0].description
    } as WeatherElement)));
  }
}