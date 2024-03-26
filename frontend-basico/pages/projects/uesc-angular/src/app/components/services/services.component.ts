import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../../services/weather.service';
import { WeatherElement } from '../../services/weather.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './services.component.html',
  styleUrl: './services.component.css'
})
export class ServicesComponent implements OnInit {

  weatherElement: WeatherElement | undefined;

  constructor (private weatherService : WeatherService) { }

  ngOnInit(): void {
    this.loadWeather();
  }

  loadWeather(): void {
    this.weatherService.getWeather().subscribe({
      next: (data) => {
        this.weatherElement = data;
      },
      
      error: (error) => {
        console.error("Erro ao buscar clima:", error);
      }
    })
  }
}