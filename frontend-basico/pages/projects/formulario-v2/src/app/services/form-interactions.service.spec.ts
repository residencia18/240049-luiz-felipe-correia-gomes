import { TestBed } from '@angular/core/testing';

import { FormInteractionsService } from './form-interactions.service';

describe('FormInteractionsService', () => {
  let service: FormInteractionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormInteractionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
