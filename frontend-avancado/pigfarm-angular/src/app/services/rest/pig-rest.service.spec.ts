import { TestBed } from '@angular/core/testing';

import { PigRestService } from './pig-rest.service';

describe('PigRestService', () => {
  let service: PigRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PigRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
