import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeightChartComponent } from './weight-chart/weight-chart.component';
import { ActivityChartComponent } from './activity-chart/activity-chart.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  pigRef: string = '';

  selectors = [
    { name: 'Weight Chart', component: WeightChartComponent },
    { name: 'Activity Chart', component: ActivityChartComponent },
  ];

  activeSelector: any = null;

  select(selector: any) {
    this.activeSelector = selector;
  }

  constructor(
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];

    if (pigRef) {
      this.pigRef = pigRef;
    }
  }
}
