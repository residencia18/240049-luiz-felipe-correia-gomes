import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentRegistrationComponent } from './treatment-registration.component';

describe('TreatmentRegistrationComponent', () => {
  let component: TreatmentRegistrationComponent;
  let fixture: ComponentFixture<TreatmentRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TreatmentRegistrationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TreatmentRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
