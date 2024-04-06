import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFireDatabase,
  AngularFireList,
} from '@angular/fire/compat/database';
import { Observable, of } from 'rxjs';
import { map, switchMap, take } from 'rxjs/operators';
import { SanitaryActivity } from 'src/app/model/activity/sanitary-activity.interface';

@Injectable({
  providedIn: 'root',
})
export class ActivitiesRestService {
  private basePath = '/pigfarm'; // Caminho para a coleção de dados no Firebase
  private itemsRef: AngularFireList<any> | undefined; // Referência para a lista de itens

  constructor(
    private db: AngularFireDatabase,
    private afAuth: AngularFireAuth
  ) {
    this.afAuth.authState.subscribe((user) => {
      if (user) {
        // The user is authenticated
        this.itemsRef = db.list(
          `${this.basePath}/${user.uid}/sanitary-activities`
        );
      } else {
        // The user is not authenticated
      }
    });
  }

  // Get all activities of the authenticated user
  getActivities(): Observable<any[]> {
    return this.afAuth.authState.pipe(
      take(1),
      switchMap((user) => {
        if (user) {
          if (this.itemsRef) {
            return this.itemsRef.snapshotChanges().pipe(
              map((changes) =>
                changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
              )
            );
          } else {
            // If the items reference is not defined, create a new one
            this.itemsRef = this.db.list(
              `${this.basePath}/${user.uid}/sanitary-activities`
            );
            return this.itemsRef.snapshotChanges().pipe(
              map((changes) =>
                changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
              )
            );
          }
        } else {
          // Return an empty observable if the user is not authenticated
          return of([]);
        }
      })
    );
  }

  // Get activities paginated
  getActivitiesPaginated(page: number, pageSize: number): Observable<any[]> {
    return this.afAuth.authState.pipe(
      take(1),
      switchMap((user) => {
        if (user) {
          if (this.itemsRef) {
            return this.itemsRef.snapshotChanges().pipe(
              map((changes) =>
                changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
              ),
              map((items) =>
                items.slice((page - 1) * pageSize, page * pageSize)
              )
            );
          } else {
            // If the items reference is not defined, create a new one
            this.itemsRef = this.db.list(
              `${this.basePath}/${user.uid}/sanitary-activities`
            );
            return this.itemsRef.snapshotChanges().pipe(
              map((changes) =>
                changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
              ),
              map((items) =>
                items.slice((page - 1) * pageSize, page * pageSize)
              )
            );
          }
        } else {
          // Return an empty observable if the user is not authenticated
          return of([]);
        }
      })
    );
  }

// Get a single activity by key
getActivityByKey(key: string): Promise<SanitaryActivity> {
  return new Promise((resolve, reject) => {
    if (this.itemsRef) {
      this.itemsRef
        .snapshotChanges()
        .pipe(
          map((changes) =>
            changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
          )
        )
        .subscribe((items) => {
          const activity = items.find((item) => item.key === key);
          if (activity) {
            resolve(activity);
          } else {
            reject('Activity not found.');
          }
        });
    } else {
      console.error('Items reference is not defined.');
      reject('Items reference is not defined.');
    }
  });
}

// Add a new activity and return the key of the new activity
addActivity(item: any): Promise<string> {
  return new Promise((resolve, reject) => {
    if (this.itemsRef) {
      this.itemsRef
        .push(item)
        .then((ref) => {
          if (ref.key) {
            resolve(ref.key); // Resolve the promise with the key of the new activity
          } else {
            reject('Key is null.'); // Reject the promise if the key is null
          }
        })
        .catch((error) => {
          console.error('Error adding activity:', error);
          reject(error); // Reject the promise if there was an error
        });
    } else {
      console.error('Items reference is not defined.');
      reject('Items reference is not defined.'); // Reject the promise if itemsRef is not defined
    }
  });
}

  // Update an existing activity
  updateActivity(key: string, value: any): Promise<string> {
    return new Promise((resolve, reject) => {
      if (this.itemsRef) {
        this.itemsRef
          .update(key, value)
          .then(() => {
            resolve(key);
          })
          .catch((error) => {
            console.error('Error updating activity:', error);
            reject(error);
          });
      } else {
        console.error('Items reference is not defined.');
        reject('Items reference is not defined.');
      }
    });
  }

  // Delete an activity
  deleteActivity(key: string): void {
    if (this.itemsRef) {
      this.itemsRef.remove(key);
    }
  }
}
