import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { PigRestService } from '../services/rest/pig-rest.service';
import { Observable, map } from 'rxjs';

export const queryParamGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const restService = inject(PigRestService);
  const pigRef = route.queryParams['pigRef'];

  if (!pigRef) {
    // If the 'pigRef' parameter is not present in the URL or is empty, redirect to another route
    router.navigate(['manager/list-pigs']);
    return new Observable<boolean | UrlTree>(observer => {
      observer.next(false);
      observer.complete();
    });
  } else {
    // Check if the item referenced by 'pigRef' exists
    return restService.pigExists(pigRef).pipe(map(exists => {
      if (!exists) {
        // If the item does not exist, redirect to another route
        router.navigate(['manager/list-pigs']);
        return false;
      } else {
        // Valid 'pigRef' parameter
        return true;
      }
    }));
  }
};
