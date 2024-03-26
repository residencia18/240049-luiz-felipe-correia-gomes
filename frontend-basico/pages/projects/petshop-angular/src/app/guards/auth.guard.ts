import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);

  if (AuthService.isAuthenticated()) {
    return true;
  } else {
    // navigate to login page
    router.navigate(['/login']);
    return false;
  }
};
