import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormInteractionsService {

  private formChanges: BehaviorSubject<any> = new BehaviorSubject<any>({});

  constructor() { }

  updateFormChanges(changes: any) {
    const currentChanges = this.formChanges.getValue();
    this.formChanges.next({ ...currentChanges, ...changes });
  }

  getFormChanges() {
    return this.formChanges.asObservable();
  }
}
