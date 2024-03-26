import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreatmentListComponent } from './treatment-list.component';

describe('TreatmentListComponent', () => {
  let component: TreatmentListComponent;
  let fixture: ComponentFixture<TreatmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TreatmentListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TreatmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
