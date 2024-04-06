import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { SanitaryActivity } from '../../../model/activity/sanitary-activity.interface';
import { ActivitiesRestService } from 'src/app/services/rest/activities-rest.service';
import { ValidationFormsService } from 'src/app/services/validation/validation-forms.service';
import { IPig } from 'src/app/model/pig/pig.interface';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';

@Component({
  selector: 'app-sanitary-management',
  templateUrl: './sanitary-management.component.html',
  styleUrls: ['./sanitary-management.component.scss'],
})
export class SanitaryManagementComponent implements OnInit {
  pigsData: IPig[] = [];
  sanitaryActivities: SanitaryActivity[] = [];
  filteredActivities: any[] = [];

  formEditActivity!: FormGroup;
  formAddActivity!: FormGroup;
  formErrors: any;

  oldPigs: IPig[] = [];
  newPigs: IPig[] = [];

  loading: boolean = false;

  avatar: string = './assets/img/avatars/pig.png';

  constructor(
    private activitiesRest: ActivitiesRestService,
    private pigsRest: PigRestService,
    private formBuilder: FormBuilder,
    private validation: ValidationFormsService
  ) {}

  ngOnInit(): void {
    this.getActivities();
    this.getPigs();
    this.formEditActivity = this.formBuilder.group({
      activity: ['', [Validators.required]],
      otherActivity: [''],
      date: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.datePattern),
        ],
      ],
      pigs: new FormControl(null, Validators.required),
      description: [''],
    });

    this.formAddActivity = this.formBuilder.group({
      activity: ['', [Validators.required]],
      otherActivity: [''],
      date: [
        '',
        [
          Validators.required,
          Validators.pattern(this.validation.formRules.datePattern),
        ],
      ],
      pigs: new FormControl(null, Validators.required),
      description: [''],
    });
  }

  onAddActivityChange(value: string) {
    if (value === 'Other') {
      this.formAddActivity.controls['otherActivity'].setValidators([
        Validators.required,
      ]);
    } else {
      this.formAddActivity.controls['otherActivity'].clearValidators();
    }
    this.formAddActivity.controls['otherActivity'].updateValueAndValidity();
  }

  onEditActivityChange(value: string) {
    if (value === 'Other') {
      this.formEditActivity.controls['otherActivity'].setValidators([
        Validators.required,
      ]);
    } else {
      this.formEditActivity.controls['otherActivity'].clearValidators();
    }
    this.formEditActivity.controls['otherActivity'].updateValueAndValidity();
  }

  // Variables to hold filter values
  pigFilter: string = '';
  activityFilter: string = '';
  dateFilter: string = '';

  // Function to apply filters
  applyFilters() {
    this.filteredActivities = this.sanitaryActivities.filter(
      (activity) =>
        (this.dateFilter === '' || activity.date === this.dateFilter) &&
        (this.activityFilter === '' ||
          activity.activity === this.activityFilter) &&
        (this.pigFilter === '' ||
          activity.pigs.some((pig) => pig.identifier === this.pigFilter))
    );
  }

  getActivities(): void {
    this.loading = true;

    this.activitiesRest
      .getActivities()
      .subscribe((data: SanitaryActivity[]) => {
        this.sanitaryActivities = data;
        // Order activities by date in descending order
        this.sanitaryActivities.sort(
          (a, b) => new Date(b.date).getTime() - new Date(a.date).getTime()
        );
        this.filteredActivities = this.sanitaryActivities;
        this.loading = false;
      });
  }

  getPigs() {
    this.pigsRest.getPigs().subscribe((response) => {
      this.pigsData = response;
    });
  }

  // Functions to control modal for add activities
  addActivityModal: boolean = false;
  addActivitySubmit: boolean = false;

  addActivityMode() {
    this.addActivitySubmit = false;
    this.toggleAddActivity();
  }

  handleAddMode(event: any) {
    this.addActivityModal = event;
  }

  toggleAddActivity() {
    this.addActivityModal = !this.addActivityModal;
  }

  cancelAddActivity() {
    this.toggleAddActivity();
  }

  confirmAdd() {
    if (this.formAddActivity.controls['activity'].value === 'Other') {
      this.formAddActivity.controls['activity'].setValue(
        this.formAddActivity.controls['otherActivity'].value
      );
    }

    this.addActivitySubmit = true;
    if (this.formAddActivity.valid) {
      const newActivity = this.formAddActivity.value;

      // Add activity for all pigs from session using the key of the new activity
      const pigs = this.formAddActivity.controls['pigs'].value;
      this.activitiesRest
        .addActivity(newActivity)
        .then((activityKey) => {
          if (pigs) {
            // Check if pigs is not null before calling forEach
            pigs.forEach((pig: IPig) => {
              const pigRef = pig.key || ''; // Ensure pigRef is always a string
              this.pigsRest.addActivityToPig(pigRef, activityKey);
            });
          } else {
            console.error('Pigs is null.');
          }
        })
        .catch((error) => {
          console.error('There was an error adding the activity:', error);
        });

      this.getActivities();
      this.toggleAddActivity();
      this.formAddActivity.reset();
    }
  }

  // Functions to control modal for view informations of activities
  viewActivityModal: boolean = false;
  viewActivityData: SanitaryActivity = {
    activity: '',
    date: '',
    pigs: [],
    description: '',
  };

  viewActivityMode(activity: SanitaryActivity) {
    this.viewActivityData = activity;
    this.toggleViewActivity();
  }

  handleViewMode(event: any) {
    this.viewActivityModal = event;
  }

  toggleViewActivity() {
    this.viewActivityModal = !this.viewActivityModal;
  }

  cancelViewActivity() {
    this.toggleViewActivity();
  }

  // Functions to control modal for edit a activity
  editActivityModal: boolean = false;
  editActivitySubmit: boolean = false;
  editActivityData!: SanitaryActivity;

  editActivityMode(activity: SanitaryActivity) {
    this.toggleEditActivity();
    this.editActivityData = activity;
    this.editActivitySubmit = false;
  }

  handleEditMode(event: any) {
    this.editActivityModal = event;
  }

  toggleEditActivity() {
    this.editActivityModal = !this.editActivityModal;
  }

  cancelEditActivity() {
    this.toggleEditActivity();
  }

  async confirmEdit() {
    this.editActivitySubmit = true;

    if (this.formEditActivity.controls['activity'].value === 'Other') {
      this.formEditActivity.controls['activity'].setValue(
        this.formEditActivity.controls['otherActivity'].value
      );
    }

    if (this.formEditActivity.valid) {
      const key = this.editActivityData.key;
      const value = this.formEditActivity.value;

      if (key !== undefined) {
        try {
          // Get the old pigs of the activity
          const oldActivity = await this.activitiesRest.getActivityByKey(key);
          this.oldPigs = oldActivity.pigs;

          await this.activitiesRest.updateActivity(key, value);

          // Add activity for all pigs from session using the key of the new activity
          const pigs = this.formEditActivity.controls['pigs'].value;
          if (pigs) {
            // Check if pigs is not null before calling forEach
            pigs.forEach((pig: IPig) => {
              const pigRef = pig.key || ''; // Ensure pigRef is always a string
              this.pigsRest.addActivityToPig(pigRef, key);
            });
          } else {
            console.error('Pigs is null.');
          }

          // Get the new pigs of the activity
          const newActivity = await this.activitiesRest.getActivityByKey(key);
          this.newPigs = newActivity.pigs;

          // Create sets for the old and new pig keys
          const oldPigKeys = new Set(this.oldPigs.map((pig) => pig.key));
          const newPigKeys = new Set(this.newPigs.map((pig) => pig.key));

          // Find the keys of the pigs that were removed
          const removedPigKeys = [...oldPigKeys].filter(
            (pigKey) => !newPigKeys.has(pigKey)
          );

          // For each removed pig, remove the activity from the pig
          removedPigKeys.forEach((pigKey) => {
            if (pigKey !== undefined) {
              this.pigsRest.removeActivityFromPig(pigKey, key);
            }
          });

          this.getActivities();
          this.toggleEditActivity();
        } catch (error) {
          console.error('There was an error:', error);
        }
      }
    }
  }

  // Functions to control modal for delete a activity
  deleteActivityModal: boolean = false;
  deleteActivityData!: SanitaryActivity;

  deleteActivityMode(activity: SanitaryActivity) {
    this.toggleDeleteActivity();
    this.deleteActivityData = activity;
  }

  handleDeleteMode(event: any) {
    this.deleteActivityModal = event;
  }

  toggleDeleteActivity() {
    this.deleteActivityModal = !this.deleteActivityModal;
  }

  cancelDeleteActivity() {
    this.toggleDeleteActivity();
  }

  confirmDelete() {
    const key = this.deleteActivityData.key;
    if (key !== undefined) {
      this.activitiesRest.deleteActivity(key);
      // Remove the activity from all pigs from session
      this.deleteActivityData.pigs.forEach((pig) => {
        const pigRef = pig.key || ''; // Ensure pigRef is always a string
        this.pigsRest.removeActivityFromPig(pigRef, key);
      });
      this.getActivities();
      this.toggleDeleteActivity();
    }
  }
}
