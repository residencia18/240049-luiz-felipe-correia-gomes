import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SanitaryManagementComponent } from './sanitary-management.component';

describe('SanitaryManagementComponent', () => {
  let component: SanitaryManagementComponent;
  let fixture: ComponentFixture<SanitaryManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SanitaryManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SanitaryManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
