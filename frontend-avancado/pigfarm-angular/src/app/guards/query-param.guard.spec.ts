import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { queryParamGuard } from './query-param.guard';

describe('queryParamGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => queryParamGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
