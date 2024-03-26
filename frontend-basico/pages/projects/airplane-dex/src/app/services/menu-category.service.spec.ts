import { TestBed } from '@angular/core/testing';

import { ManagerDataService } from './manager-data.service';

describe('ManagerDataService', () => {
  let service: ManagerDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManagerDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
