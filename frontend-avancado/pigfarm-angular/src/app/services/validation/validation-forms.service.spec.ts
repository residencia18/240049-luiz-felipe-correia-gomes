import { TestBed } from '@angular/core/testing';

import { ValidationFormsService } from './validation-forms.service';

describe('ValidationFormsService', () => {
  let service: ValidationFormsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValidationFormsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
