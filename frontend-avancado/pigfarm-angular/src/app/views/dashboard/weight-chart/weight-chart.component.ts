import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; // Importe ActivatedRoute

import { PigRestService } from 'src/app/services/rest/pig-rest.service';
import { IPig } from 'src/app/model/pig/pig.interface';
import {
  BubbleDataPoint,
  ChartData,
  ChartTypeRegistry,
  CoreChartOptions,
  DatasetChartOptions,
  ElementChartOptions,
  PluginChartOptions,
  ScaleChartOptions,
  ScatterDataPoint,
} from 'chart.js';

import { FormatDatePipe } from 'src/app/services/pipes/format-date.pipe';
import { _DeepPartialObject } from 'chart.js/types/utils';

@Component({
  templateUrl: 'weight-chart.component.html',
  styleUrls: ['weight-chart.component.scss'],
})
export class WeightChartComponent implements OnInit {
  data:
    | ChartData<
        keyof ChartTypeRegistry,
        (number | ScatterDataPoint | BubbleDataPoint | null)[],
        unknown
      >
    | undefined;

  chartSetup:
    | _DeepPartialObject<
        CoreChartOptions<keyof ChartTypeRegistry> &
          ElementChartOptions<keyof ChartTypeRegistry> &
          PluginChartOptions<keyof ChartTypeRegistry> &
          DatasetChartOptions<keyof ChartTypeRegistry> &
          ScaleChartOptions<keyof ChartTypeRegistry>
      >
    | undefined;

  constructor(
    private activatedRoute: ActivatedRoute,
    private restService: PigRestService,
    private formatDate: FormatDatePipe,
    private router: Router
  ) {}

  pigSelected!: IPig;
  existsWeightHistory: boolean = false;
  loading: boolean = false;
  pigRef: string = '';

  ngOnInit(): void {
    const pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];

    this.chartSetup = {
      scales: {
        y: {
          suggestedMax: 100,
          beginAtZero: true,
        },
      },
    };

    if (pigRef) {
      this.pigRef = pigRef;
      this.loadPigAndInitChart(pigRef);
    }
  }

  loadPigAndInitChart(pigRef: string): void {
    this.loading = true;

    this.restService.getPigByID(pigRef).subscribe((pig: IPig) => {
      this.pigSelected = pig;
      if (pig.weightHistory) {
        this.initChartForSinglePig(pig.weightHistory);
        this.existsWeightHistory = true;
      } else {
        this.existsWeightHistory = false;
        this.loading = false;
      }
    });
  }

  initChartForSinglePig(
    weightHistory: { date: string; weight: string }[]
  ): void {
    // Convert weightHistory to an array using Object.values()
    const weightHistoryArray = Object.values(weightHistory);

    const combinedDatesAndWeights = weightHistoryArray.map((entry) => ({
      date: entry.date,
      weight: parseFloat(entry.weight),
    }));

    combinedDatesAndWeights.sort((a, b) => {
      if (a.date < b.date) {
        return -1;
      }
      if (a.date > b.date) {
        return 1;
      }
      return 0;
    });

    // Extract dates and weights from combinedDatesAndWeights
    let dates = combinedDatesAndWeights.map((entry) => entry.date);
    let weights = combinedDatesAndWeights.map((entry) => entry.weight);

    dates = dates.map((date) => this.formatDate.transform(date));

    this.data = {
      labels: dates,
      datasets: [
        {
          label: 'Weight',
          backgroundColor: 'rgb(235, 89, 110, 0.5)',
          borderColor: 'rgb(235, 89, 110, 0.5)',
          pointBackgroundColor: 'rgb(235, 89, 110, 1)',
          pointBorderColor: '#fff',
          data: weights,
        },
      ],
    };

    this.loading = false;
  }

  weightControl(): void {
    this.router.navigate(['manager/weight-control'], {
      queryParams: { pigRef: this.pigRef },
    });
  }
}
