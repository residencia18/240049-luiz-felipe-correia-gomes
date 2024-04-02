import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFireDatabase,
  AngularFireList,
} from '@angular/fire/compat/database';
import { Observable, firstValueFrom, of } from 'rxjs';
import { map, switchMap, take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PigRestService {
  private basePath = '/pigfarm'; // Caminho para a coleção de dados no Firebase
  private itemsRef: AngularFireList<any> | undefined; // Referência para a lista de itens

  constructor(
    private db: AngularFireDatabase,
    private afAuth: AngularFireAuth
  ) {
    this.afAuth.authState.subscribe((user) => {
      if (user) {
        // The user is authenticated
        this.itemsRef = db.list(`${this.basePath}/${user.uid}/pigs`);
      } else {
        // The user is not authenticated
      }
    });
  }

    // Get all pigs
    getPigs(): Observable<any[]> {
      return this.afAuth.authState.pipe(
        take(1),
        switchMap(user => {
          if (user) {
            if (this.itemsRef) {
              return this.itemsRef.snapshotChanges().pipe(
                map(changes => changes.map(c => ({ key: c.payload.key, ...c.payload.val() })))
              );
            } else {
              // If the items reference is not defined, create a new one
              this.itemsRef = this.db.list(`${this.basePath}/${user.uid}/pigs`);
              return this.itemsRef.snapshotChanges().pipe(
                map(changes => changes.map(c => ({ key: c.payload.key, ...c.payload.val() })))
              );
            }
          } else {
            // Return an empty observable if the user is not authenticated
            return of([]);
          }
        })
      );
    }

    // Get pigs paginated
    getPigsPaginated(page: number, pageSize: number): Observable<any[]> {
      return this.afAuth.authState.pipe(
        take(1),
        switchMap(user => {
          if (user) {
            if (this.itemsRef) {
              return this.itemsRef.snapshotChanges().pipe(
                map(changes => changes.map(c => ({ key: c.payload.key, ...c.payload.val() }))),
                map(items => items.slice((page - 1) * pageSize, page * pageSize))
              );
            } else {
              // If the items reference is not defined, create a new one
              this.itemsRef = this.db.list(`${this.basePath}/${user.uid}/pigs`);
              return this.itemsRef.snapshotChanges().pipe(
                map(changes => changes.map(c => ({ key: c.payload.key, ...c.payload.val() }))),
                map(items => items.slice((page - 1) * pageSize, page * pageSize))
              );
            }
          } else {
            // Return an empty observable if the user is not authenticated
            return of([]);
          }
        })
      );
    }


    // Get a single pig by its key
    getPigByID(key: string): Observable<any> {
      return this.afAuth.authState.pipe(
        switchMap((user) => {
          if (user) {
            return this.db
              .object(`${this.basePath}/${user.uid}/pigs/${key}`)
              .valueChanges();
          } else {
            return new Observable<any>();
          }
        })
      );
    }

    // Add a new pig
    addPig(item: any): void {
      if (this.itemsRef) {
        this.itemsRef.push(item);
      }
    }

    // Update an existing pig
    updatePig(key: string, value: any): void {
      if (this.itemsRef) {
        this.itemsRef.update(key, value);
      }
    }

    // Delete an existing pig
    deletePig(key: string): void {
      if (this.itemsRef) {
        this.itemsRef.remove(key);
      }
    }

    // Check if pig exists
    pigExists(key: string): Observable<boolean> {
      return this.getPigByID(key).pipe(
        map(item => !!item) // Convert the item to a boolean value
      );
    }



    /* Weight History Operations */

    // Add a new weight to a pig
    addWeightToPig(pigRef: string, value: any): void {
      const { weight, date } = value;

      if (this.itemsRef) {
        this.afAuth.authState.subscribe((user) => {
          if (user) {
            const weightPath = `${this.basePath}/${user.uid}/pigs/${pigRef}/weightHistory`;
            const newWeightRef = this.db.list(weightPath);

            const newWeight = {
              weight: weight,
              date: date,
            };

            newWeightRef
              .push(newWeight)
              .then(() => {
                console.log('Peso adicionado com sucesso ao documento do porco.');
              })
              .catch((error) => {
                console.error(
                  'Erro ao adicionar peso ao documento do porco:',
                  error
                );
              });
          } else {
            console.error('Usuário não autenticado.');
          }
        });
      } else {
        console.error('Referência de itens não está definida.');
      }
    }

    // Get the weight history of a pig
    getWeightHistory(pigRef: string): Observable<any> {
      if (this.itemsRef) {
        return this.afAuth.authState.pipe(
          take(1),
          switchMap(user => {
            if (user) {
              return this.db.list<any>(`${this.basePath}/${user.uid}/pigs/${pigRef}/weightHistory`).valueChanges();
            } else {
              return of(null);
            }
          })
        );
      }
      return new Observable<any[]>();
    }

    // Update the weight history of a pig
    updateWeightHistory(pigRef: string, updatedWeightHistory: any): Promise<void> {
      return firstValueFrom(this.afAuth.authState.pipe(
        take(1),
        switchMap(user => {
          if (user) {
            return this.db.object(`${this.basePath}/${user.uid}/pigs/${pigRef}/weightHistory`).set(updatedWeightHistory);
          } else {
            return Promise.resolve(); // Retorne uma promessa vazia se o usuário não estiver autenticado
          }
        })
      ));
    }

}
