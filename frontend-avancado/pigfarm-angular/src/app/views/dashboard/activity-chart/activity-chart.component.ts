import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ChartData,
  ChartTypeRegistry,
  ScatterDataPoint,
  BubbleDataPoint,
  CoreChartOptions,
  DatasetChartOptions,
  ElementChartOptions,
  PluginChartOptions,
  ScaleChartOptions,
} from 'chart.js';
import { _DeepPartialObject } from 'chart.js/types/utils';
import { forkJoin, from, tap, of } from 'rxjs';
import {
  IActivity,
  SanitaryActivity,
} from 'src/app/model/activity/sanitary-activity.interface';
import { IWeight } from 'src/app/model/activity/weight.interface';
import { IPig } from 'src/app/model/pig/pig.interface';
import { ActivitiesRestService } from 'src/app/services/rest/activities-rest.service';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';

@Component({
  templateUrl: './activity-chart.component.html',
  styleUrl: './activity-chart.component.scss',
})
export class ActivityChartComponent {
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
    private pigsRestService: PigRestService,
    private activitiesRestService: ActivitiesRestService,
    private router: Router
  ) {}

  pigSelected!: IPig;

  sanitaryActivities: SanitaryActivity[] = [];
  weightHistory: IWeight[] = [];
  activities: IActivity[] = [];

  existsHistory: boolean = false;
  loading: boolean = false;
  pigRef: string = '';

  ngOnInit(): void {
    const pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];

    this.chartSetup = {
      scales: {
        y: {
          suggestedMax: 10,
          beginAtZero: true,
          ticks: {
            stepSize: 1,
          }
        }
      }
    };

    this.loading = true;

    this.loadData(pigRef);
  }

  loadPigAndInitChart(pigRef: string): void {
    this.loading = true;

    this.pigsRestService.getPigByID(pigRef).subscribe((pig: IPig) => {
      this.pigSelected = pig;
      if (pig.activityHistory) {
        this.initChartForSinglePig();
        this.existsHistory = true;
      } else {
        this.existsHistory = false;
        this.loading = false;
      }
    });
  }

  initChartForSinglePig(): void {
    console.log(this.activities);

    // Sort activities and count the number of occurrences of each activity
    const activitiesGrouped = this.activities.reduce(
      (acc: { [key: string]: number }, activity) => {
        if (acc[activity.activity]) {
          acc[activity.activity] += 1;
        } else {
          acc[activity.activity] = 1;
        }
        return acc;
      },
      {}
    );

    // Extract the activities and counts to create the chart
    const activities = Object.keys(activitiesGrouped);
    const counts = Object.values(activitiesGrouped);

    this.data = {
      labels: activities,
      datasets: [
        {
          label: 'Activity Count',
          backgroundColor: 'rgb(235, 89, 110, 0.5)',
          borderColor: 'rgb(235, 89, 110, 1)',
          pointBackgroundColor: 'rgb(244, 172, 186, 1)',
          data: counts,
        },
      ],
    };

    this.loading = false;
  }

  loadData(pigRef: string) {
    this.pigsRestService.getPigByID(pigRef).subscribe((pig: IPig) => {
      this.pigSelected = pig;

      // Convert getSanitaryActivities and getWeightHistory to return Observables
      const sanitaryActivities$ = this.getSanitaryActivities();
      const weightHistory$ = this.getWeightHistory();

      // Use forkJoin to wait for all observables to complete
      forkJoin({
        sanitaryActivities: sanitaryActivities$,
        weightHistory: weightHistory$,
      }).subscribe(() => {
        this.loading = false;
        if (pigRef) {
          this.pigRef = pigRef;
          this.loadPigAndInitChart(pigRef);
        }
      });
    });
  }

  getSanitaryActivities() {
    if (this.pigSelected.activityHistory) {
      const activitiesKeys: { ref: string }[] = Object.values(
        this.pigSelected.activityHistory
      );

      // Convert each promise to an observable and return an array of observables
      const observables = activitiesKeys.map((activity) =>
        from(this.activitiesRestService.getActivityByKey(activity.ref)).pipe(
          tap((sanitaryActivity: SanitaryActivity) => {
            this.sanitaryActivities.push(sanitaryActivity);

            // Add the sanitary activity to the activities array
            this.activities.push({
              date: sanitaryActivity.date,
              activity: sanitaryActivity.activity,
              description: sanitaryActivity.description,
              weight: 'N/A',
            });
          })
        )
      );

      // Use forkJoin to wait for all observables to complete and return the result as an observable
      return forkJoin(observables);
    } else {
      this.sanitaryActivities = [];
      return of([]); // Return an observable that immediately completes
    }
  }

  getWeightHistory() {
    if (this.pigSelected.weightHistory) {
      this.weightHistory = Object.values(this.pigSelected.weightHistory);
    } else {
      this.weightHistory = [];
    }

    // Add weight activities to the activities array
    this.weightHistory.forEach((weight) => {
      this.activities.push({
        date: weight.date,
        activity: 'Weighing',
        weight: weight.weight,
        description: '',
      });
    });

    return of(this.weightHistory); // Return an observable that immediately completes
  }

  activityHistory():void {
    this.router.navigate(['manager/pig-history'], {
      queryParams: { pigRef: this.pigRef },
    });
  }
}
