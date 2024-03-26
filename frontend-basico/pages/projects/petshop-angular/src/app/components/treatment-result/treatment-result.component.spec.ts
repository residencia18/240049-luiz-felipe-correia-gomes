import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentResultComponent } from './treatment-result.component';

describe('TreatmentResultComponent', () => {
  let component: TreatmentResultComponent;
  let fixture: ComponentFixture<TreatmentResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TreatmentResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TreatmentResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
