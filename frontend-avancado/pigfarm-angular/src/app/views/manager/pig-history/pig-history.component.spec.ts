import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PigHistoryComponent } from './pig-history.component';

describe('PigHistoryComponent', () => {
  let component: PigHistoryComponent;
  let fixture: ComponentFixture<PigHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PigHistoryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PigHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
