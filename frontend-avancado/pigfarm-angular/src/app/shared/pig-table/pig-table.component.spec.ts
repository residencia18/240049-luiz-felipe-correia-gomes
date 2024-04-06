import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PigTableComponent } from './pig-table.component';

describe('PigTableComponent', () => {
  let component: PigTableComponent;
  let fixture: ComponentFixture<PigTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PigTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PigTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
