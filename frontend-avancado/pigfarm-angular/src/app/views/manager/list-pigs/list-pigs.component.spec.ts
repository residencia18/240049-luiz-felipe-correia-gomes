import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPigsComponent } from './list-pigs.component';

describe('ListPigsComponent', () => {
  let component: ListPigsComponent;
  let fixture: ComponentFixture<ListPigsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListPigsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListPigsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
