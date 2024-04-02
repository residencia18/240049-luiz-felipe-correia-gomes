import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getFirstCountry(): Observable<any> {
    return this.http.get('https://restcountries.com/v3.1/all').pipe(
      map((data: any) => data[0])
    );
  }
}
