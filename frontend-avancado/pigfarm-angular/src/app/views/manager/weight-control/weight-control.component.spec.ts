import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightControlComponent } from './weight-control.component';

describe('WeightControlComponent', () => {
  let component: WeightControlComponent;
  let fixture: ComponentFixture<WeightControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WeightControlComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WeightControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
