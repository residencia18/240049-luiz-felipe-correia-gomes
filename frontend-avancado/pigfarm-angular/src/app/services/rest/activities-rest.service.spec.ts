import { TestBed } from '@angular/core/testing';

import { ActivitiesRestService } from './activities-rest.service';

describe('ActivitiesRestService', () => {
  let service: ActivitiesRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivitiesRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
