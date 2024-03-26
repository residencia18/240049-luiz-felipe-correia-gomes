import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentSearchComponent } from './treatment-search.component';

describe('TreatmentSearchComponent', () => {
  let component: TreatmentSearchComponent;
  let fixture: ComponentFixture<TreatmentSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TreatmentSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TreatmentSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
