import { TestBed } from '@angular/core/testing';

import { FormInteractionService } from './form-interaction.service';

describe('FormInteractionService', () => {
  let service: FormInteractionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormInteractionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
