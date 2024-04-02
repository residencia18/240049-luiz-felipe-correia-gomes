import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFireDatabase,
  AngularFireList,
} from '@angular/fire/compat/database';
import { Observable, of } from 'rxjs';
import { map, switchMap, take } from 'rxjs/operators';

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
  getActivityByKey(key: string): Observable<any> {
    return this.afAuth.authState.pipe(
      switchMap((user) => {
        if (user) {
          return this.db
            .object(`${this.basePath}/${user.uid}/sanitary-activities/${key}`)
            .valueChanges();
        } else {
          return new Observable<any>();
        }
      })
    );
  }

  // Add a new activity
  addActivity(item: any): void {
    if (this.itemsRef) {
      this.itemsRef.push(item);
    }
  }

  // Update an existing activity
  updateActivity(key: string, value: any): void {
    if (this.itemsRef) {
      this.itemsRef.update(key, value);
    }
  }

  // Delete an activity
  deleteActivity(key: string): void {
    if (this.itemsRef) {
      this.itemsRef.remove(key);
    }
  }
}
