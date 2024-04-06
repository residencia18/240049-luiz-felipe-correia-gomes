import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { forkJoin, of, tap } from 'rxjs';
import { SanitaryActivity, IActivity } from 'src/app/model/activity/sanitary-activity.interface';
import { IWeight } from 'src/app/model/activity/weight.interface';
import { IPig } from 'src/app/model/pig/pig.interface';
import { ActivitiesRestService } from 'src/app/services/rest/activities-rest.service';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-pig-history',
  templateUrl: './pig-history.component.html',
  styleUrl: './pig-history.component.scss',
})

export class PigHistoryComponent implements OnInit {
  sanitaryActivities: SanitaryActivity[] = [];
  weightHistory: IWeight[] = [];
  activities: IActivity[] = [];
  pigSelected!: IPig;
  pigRef: string = '';

  filteredActivities: IActivity[] = [];

  loading: boolean = false;

  avatar: string = './assets/img/avatars/pig.png';

  constructor(
    private pigsRestService: PigRestService,
    private activitiesRestService: ActivitiesRestService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const pigRef = this.activatedRoute.snapshot.queryParams['pigRef'];

    this.loading = true;

    if (pigRef) {
      this.pigRef = pigRef;
      this.loadData(pigRef);
    }
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
        // Sort activities by date in descending order
        this.activities.sort(
          (a, b) => new Date(b.date).getTime() - new Date(a.date).getTime()
        );
        this.filteredActivities = this.activities;

        this.loading = false;
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

  // Variables to hold filter values
  dateFilter: string = '';
  activityFilter: string = '';
  weightFilter: string = '';

  // Function to apply filters
  applyFilters() {
    this.filteredActivities = this.activities.filter(
      (activity) =>
        (this.dateFilter === '' || activity.date === this.dateFilter) &&
        (this.activityFilter === '' ||
          activity.activity.includes(this.activityFilter)) &&
        (this.weightFilter === '' ||
          (activity.weight ?? '').includes(this.weightFilter))
    );
  }

  showChart(): void {
    this.router.navigate(['/dashboard'], {
      queryParams: { pigRef: this.pigRef },
    });
  }
}
